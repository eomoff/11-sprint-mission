package com.sprint.mission.discodeit;

import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DiscodeitApplication {
	private static final Logger logger = LoggerFactory.getLogger(DiscodeitApplication.class);

	// 테스트용 유저 생성
	static User setupUser(UserService userService) {
		return userService.create("woody");
	}

	// 테스트용 채널 생성
	static Channel setupChannel(ChannelService channelService) {
		return channelService.create("공지");
	}

	// 메세지 생성 테스트
	static void messageCreateTest(MessageService messageService, Channel channel, User author) {
		Message message = messageService.create("안녕하세요", channel.getId(), author.getId());
		logger.info("메세지 생성: {}", message.getId());
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DiscodeitApplication.class, args);

		// 서비스 초기화
		UserService userService;
		ChannelService channelService;
		MessageService messageService;

		// context에서 Bean 조회
		userService = context.getBean(UserService.class);
		channelService = context.getBean(ChannelService.class);
		messageService = context.getBean(MessageService.class);

		// 셋업
		User user = setupUser(userService);
		Channel channel = setupChannel(channelService);

		// 테스트
		messageCreateTest(messageService, channel, user);
	}

}
