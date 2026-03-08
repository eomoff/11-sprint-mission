package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.MessageService;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;


public class FileMessageService implements MessageService {
    // 메세지 데이터를 저장할 디렉토리 경로
    private final Path directory;

    // 생성자: 저장 디렉토리 경로를 받아 초기화하고, 디렉토리가 없으면 생성하는거
    public FileMessageService(String directoryPath) {
        this.directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            try {
                Files.createDirectories(this.directory);
            } catch (IOException e) {
                throw new RuntimeException("디렉토리 생성 실패: " + directory, e);
            }
        }
    }

    // Message 객체를 직렬화하여 파일(.ser)로 저장하는 내부 메소드임
    private void saveToFile(Message message) {
        Path filePath = directory.resolve(message.getId().toString() + ".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            oos.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + filePath, e);
        }
    }

    // 파일(.ser)에서 Message 객체를 역직렬화하여 읽어오는 내부 메소드임
    private Message loadFromFile(Path filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (Message) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("파일 읽기 실패" + filePath, e);
        }
    }

    // 새로운 Message를 생성하고 파일로 저장한 뒤 반환하는 기능임
    @Override
    public Message create(String content) {
        Message message = new Message(content);
        saveToFile(message);
        return message;
    }

    // UUID로 특정 Message를 조회 (파일이 없으면 null 반환)
    @Override
    public Message find(UUID id) {
        Path filePath = directory.resolve(id.toString() + ".ser");
        if (!Files.exists(filePath)) {
            return null;
        }
        return loadFromFile(filePath);
    }

    // 디렉토리 내 모든 .ser 파일을 읽어 전체 Message 목록을 변환
    @Override
    public List<Message> findAll() {
        try {
            return Files.list(directory)
                    .filter(path -> path.toString().endsWith(".ser"))
                    .map(this::loadFromFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("파일 목록 조회 실패", e);
        }
    }

    // UUID로 Message를 찾아 내용을 수정하고, 변경된 내용을 파일에 다시 저장하는거
    @Override
    public Message update(UUID id, String content) {
        Message message = find(id);
        if (message != null) {
            message.update(content);
            saveToFile(message);
        }
        return message;
    }

    // UUID에 해당하는 Message 파일을 삭제하는거
    @Override
    public void delete(UUID id) {
        Path filePath = directory.resolve(id.toString() + ".ser");
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 실패: " + filePath, e);
        }
    }

}
