package com.sprint.mission.discodeit.config;

import com.sprint.mission.discodeit.repository.ChannelRepository;
import com.sprint.mission.discodeit.repository.MessageRepository;
import com.sprint.mission.discodeit.repository.UserRepository;
import com.sprint.mission.discodeit.repository.file.FileChannelRepository;
import com.sprint.mission.discodeit.repository.file.FileMessageRepository;
import com.sprint.mission.discodeit.repository.file.FileUserRepository;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.basic.BasicChannelService;
import com.sprint.mission.discodeit.service.basic.BasicMessageService;
import com.sprint.mission.discodeit.service.basic.BasicUserService;
import org.springframework.context.annotation.Bean;

public class DiscodeitBeanConfig {

    @Bean
    public UserRepository userRepository() {
        return new FileUserRepository("data/users");
    }

    @Bean
    public ChannelRepository channelRepository() {
        return new FileChannelRepository("data/channels");
    }

    @Bean
    public MessageRepository messageRepository() {
        return new FileMessageRepository("data/messages");
    }

    @Bean
    public UserService userService(UserRepository userRepository) {
        return new BasicUserService(userRepository);
    }

    @Bean
    public ChannelService channelService(ChannelRepository channelRepository) {
        return new BasicChannelService(channelRepository);
    }

    @Bean
    public MessageService messageService(MessageRepository messageRepository,
                                         UserRepository userRepository,
                                         ChannelRepository channelRepository) {
        return new BasicMessageService(messageRepository, userRepository, channelRepository);
    }
}
