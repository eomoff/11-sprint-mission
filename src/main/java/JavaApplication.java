import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.entity.Channel;
import com.sprint.mission.discodeit.entity.Message;
import com.sprint.mission.discodeit.service.ChannelService;
import com.sprint.mission.discodeit.service.MessageService;
import com.sprint.mission.discodeit.service.UserService;
import com.sprint.mission.discodeit.service.jcf.JCFChannelService;
import com.sprint.mission.discodeit.service.jcf.JCFMessageService;
import com.sprint.mission.discodeit.service.jcf.JCFUserService;
import java.util.List;

public class JavaApplication {
    public static void main(String[] args) {
        UserService userService = new JCFUserService();
        ChannelService channelService = new JCFChannelService();
        MessageService messageService = new JCFMessageService();

        testUser(userService);
        testChannel(channelService);
        testMessage(messageService);

    }
    // User 테스트
    static void testUser(UserService userService) {
        System.out.println("-- User 테스트 --");

        // 등록하기
        User user = userService.create("김신의");
        System.out.println("[등록]: " + user.getName());

        // 조회
        User found = userService.find(user.getId());
        System.out.println("[단건 조회]: " + found.getName());

        // 다건 조회
        userService.create("황정혜");
        userService.create("김영희");
        List<User> users = userService.findAll();
        System.out.println("[다건 조회]: 총 " + users.size() + "명");

        // 수정
        userService.update(user.getId(), "김신의(수정됨)");

        // 수정된 데이터 조회
        User updated = userService.find(user.getId());
        System.out.println("[수정 후 조회]: " + updated.getName());

        // 삭제
        userService.delete(user.getId());

        // 삭제 확인
        User deleted = userService.find(user.getId());
        System.out.println("[삭제 확인]: " + (deleted == null ? "삭제됨" : "삭제 안됨"));
    }

    // Channel 테스트
    static void testChannel(ChannelService channelService) {
        System.out.println("--- Channel 테스트 ---");

        // 등록
        Channel channel = channelService.create("일반");
        System.out.println("[등록]: " + channel.getName());

        // 단건 조회
        Channel found = channelService.find(channel.getId());
        System.out.println("[단건 조회]: " + found.getName());

        // 다건 조회
        channelService.create("공지사항");
        channelService.create("자유게시판");
        List<Channel> channels = channelService.findAll();
        System.out.println("[다건 조회]: 총 " + channels.size() + "개");

        // 수정
        channelService.update(channel.getId(), "일반(수정됨)");

        // 수정된 데이터 조회
        Channel updated = channelService.find(channel.getId());
        System.out.println("[수정 후 조회]: " + updated.getName());

        // 삭제
        channelService.delete(channel.getId());

        // 삭제 확인
        Channel deleted = channelService.find(channel.getId());
        System.out.println("[삭제 확인]: " + (deleted == null ? "삭제됨" : "삭제안됨"));
    }

    // Message 테스트
    static void testMessage(MessageService messageService) {
        System.out.println("--- Message 테스트 ---");

        // 등록
        Message message = messageService.create("안녕하세요!");
        System.out.println("[등록]: " + message.getContent());

        // 단건 조회
        Message found = messageService.find(message.getId());
        System.out.println("[단건 조회]: " + found.getContent());

        // 다건 조회
        messageService.create("안녕하세요");
        messageService.create("코드잇 백엔드 11기 입니다");
        List<Message> messages = messageService.findAll();
        System.out.println("[다건 조회]: 총 " + messages.size() + "개");

        // 수정
        messageService.update(message.getId(), "안녕하세요!(수정됨)");

        // 수정된 데이터 조회
        Message updated = messageService.find(message.getId());
        System.out.println("[수정 후 조회]: " + updated.getContent());

        // 삭제
        messageService.delete(message.getId());

        Message deleted = messageService.find(message.getId());
        System.out.println("[삭제 확인]: " + (deleted == null ? "삭제됨" : "삭제 안됨"));
    }
}
