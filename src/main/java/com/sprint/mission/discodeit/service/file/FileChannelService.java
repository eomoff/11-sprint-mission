package com.sprint.mission.discodeit.service.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.service.ChannelService;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileChannelService implements ChannelService {

    // 채널 데이터를 저장할 디렉토리 경로임
    private final Path directory;


    // 생성자: 저장 디렉토리 경로를 받아 초기화하고, 디렉토리가 없으면 생성하는 기능임
    public FileChannelService(String directoryPath) {
        this.directory = Paths.get(directoryPath);
        if (!Files.exists(this.directory)) {
            try {
                Files.createDirectories(this.directory);
            } catch (IOException e) {
                throw new RuntimeException("디렉토리 생성 실패: " + directoryPath, e);
            }
        }
    }

    // Channel 객체를 직렬화하여 파일(.ser)로 저장하는 기능임
    private void saveToFile(Channel channel) {
        Path filePath = directory.resolve(channel.getId().toString() + ".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream((new FileOutputStream((filePath.toFile()))))) {
            oos.writeObject(channel);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + filePath, e);
        }
    }

    // 파일(.ser)에서 Channel 객체를 역직렬화 하여 읽어오는 기능임
    private Channel loadFromFile(Path filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (Channel) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("파일 읽기 실해: " + filePath, e);
        }
    }

    // 새로운 Channel을 생성하고 파일로 저장한 뒤 변환하는거임
    @Override
    public Channel create(String name) {
        Channel channel = new Channel(name);
        saveToFile(channel);
        return channel;
    }

    // UUID로 특정 Channel을 조회 (파일이 없으면 null 반환)
    @Override
    public Channel find(UUID id) {
        Path filePath = directory.resolve(id.toString() + ".ser");
        if (!Files.exists(filePath)) {
            return null;
        }
        return loadFromFile(filePath);
    }

    // 디렉토리 내 모든 .ser 파일을 읽어 전체 Channel 목록을 반환
    @Override
    public List<Channel> findAll() {
        try {
            return Files.list(directory)
                    .filter(path -> path.toString().endsWith(".ser"))
                    .map(this::loadFromFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("파일 목록 조회 실패", e);
        }
    }

    // UUID로 Channel을 찾아 이름을 수정하고, 변경된 내용을 파일에 다시 저장
    @Override
    public Channel update(UUID id, String name) {
        Channel channel = find(id);
        if (channel != null) {
            channel.update(name);
            saveToFile(channel);
        }
        return channel;
    }

    // UUID에 해당하는 Channel 파일을 삭제
    @Override
    public void delete(UUID id) {
        Path filePath = directory.resolve(id.toString() + ".ser");
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException("파일 삭제 실해: " + filePath, e);
        }
    }
}
