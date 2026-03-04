package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileUserService implements UserService {
    // 유저 데이터를 저장할 디렉토리 경로
    private final Path directory;

    // 생성자: 저장 디렉토리 경로를 받아 초기화하고, 디렉토리가 없으면 생성
    public FileUserService(String directoryPath) {
        this.directory = Paths.get(directoryPath);
        if (!Files.exists(this.directory)) {
            try {
                Files.createDirectories(this.directory);
            } catch (IOException e) {
                throw new RuntimeException("디렉토리 생성 실패" + directoryPath, e);
            }
        }
    }

    // User 객체를 직렬화하여 파일(.ser)로 저장하는 내부 메소드임
    private void saveToFile(User user) {
        Path filePath = directory.resolve(user.getId().toString() + ".set");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + filePath, e);
        }
    }

    // 파일(.ser)에서 User 객체를 역직렬화하여 읽어오는 내부 메소드임
    private User loadFromFile(Path filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (User) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("파일 읽기 실패: " + filePath, e);
        }
    }

    // 새로운 User를 생성하고 파일로 저장한 뒤 반환하는거
    @Override
    public User create(String name) {
        User user = new User(name);
        saveToFile(user);
        return user;
    }

    // UUID로 특정 User를 조회 (파일이 없으면 null 반환)
    @Override
    public User find(UUID id) {
        Path filePath = directory.resolve(id.toString() + ".set");
        if (!Files.exists(filePath)) {
            return null;
        }
        return loadFromFile(filePath);
    }

    // 디렉토리 내 모든 .ser 파일을 읽어 전체 User 목록을 반환
    @Override
    public List<User> findAll() {
        try {
            return Files.list(directory)
                    .filter(path -> path.toString().endsWith(".ser"))
                    .map(this::loadFromFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("파일 목록 조회 실패", e);
        }
    }

    // UUID로 User를 찾아 이름을 수정하고, 변경된 내용을 파일에 다시 저장하는거
    @Override
    public User update(UUID id, String name) {
        User user = find(id);
        if (user != null) {
            user.update(name);
            saveToFile(user);
        }
        return user;
    }

    @Override
    public void delete(UUID id) {
        Path filePath = directory.resolve(id.toString() + ".set");
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 실패: " + filePath, e);
        }
    }
}
