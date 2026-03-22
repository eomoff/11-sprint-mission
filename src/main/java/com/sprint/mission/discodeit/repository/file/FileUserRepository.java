package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.repository.UserRepository;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileUserRepository implements UserRepository {
    private static final String FILE_EXTENSION = ".ser";
    private final Path directory;

    public FileUserRepository(String directoryPath) {
        this.directory = Paths.get(directoryPath);
        if (!Files.exists(this.directory)) {
            try {
                Files.createDirectories(this.directory);
            } catch (IOException e) {
                throw new RuntimeException("디렉토리 생성 실패: " + directoryPath, e);
            }
        }
    }

    private void saveToFile(User user) {
        Path filePath = directory.resolve(user.getId().toString() + FILE_EXTENSION);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            oos.writeObject(user);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + filePath, e);
        }
    }

    private User loadFromFile(Path filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return(User) ois.readObject();
        } catch(IOException | ClassNotFoundException e) {
            throw new RuntimeException("파일 읽기 실해: " + filePath, e);
        }
    }

    @Override
    public User save(User user) {
        saveToFile(user);
        return user;
    }

    @Override
    public User findById(UUID id) {
        Path filePath = directory.resolve(id.toString() + FILE_EXTENSION);
        if (!Files.exists(filePath)) {
            return null;
        }
        return loadFromFile(filePath);
    }

    @Override
    public List<User> findAll() {
        try {
            return Files.list(directory)
                    .filter(path -> path.toString().endsWith(FILE_EXTENSION))
                    .map(this::loadFromFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("파일 목록 조회 실패", e);
        }
    }

    @Override
    public void delete(UUID id) {
        Path filePath = directory.resolve(id.toString() + FILE_EXTENSION);
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 실패: " + filePath, e);
        }
    }
}
