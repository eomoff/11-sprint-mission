package com.sprint.mission.discodeit.repository.file;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.repository.ChannelRepository;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileChannelRepository implements ChannelRepository{
    private static final String FILE_EXTENSION = ".ser";
    private final Path directory;

    public FileChannelRepository(String directoryPath) {
        this.directory = Paths.get(directoryPath);
        if (!Files.exists(this.directory)) {
            try {
                Files.createDirectories(this.directory);
            } catch (IOException e) {
                throw new RuntimeException("디렉토리 생성 실패: " + directoryPath, e);
            }
        }
    }

    private void saveToFile(Channel channel) {
        Path filePath = directory.resolve(channel.getId().toString() + FILE_EXTENSION);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath.toFile()))) {
            oos.writeObject(channel);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패: " + filePath, e);
        }
    }

    private Channel loadFromFile(Path filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath.toFile()))) {
            return (Channel) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("파일 읽기 실패: " + filePath, e);
        }
    }

    @Override
    public Channel save(Channel channel) {
        saveToFile(channel);
        return channel;
    }

    @Override
    public Channel findById(UUID id) {
        Path filePath = directory.resolve(id.toString() + FILE_EXTENSION);
        if (!Files.exists(filePath)) {
            return null;
        }
        return loadFromFile(filePath);
    }

    @Override
    public List<Channel> findAll() {
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
