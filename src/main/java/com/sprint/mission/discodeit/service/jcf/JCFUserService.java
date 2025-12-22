package com.sprint.mission.discodeit.service.jcf;

import com.sprint.mission.discodeit.entity.User;
import com.sprint.mission.discodeit.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class JCFUserService implements UserService {
    // JCF를 활용하여 데이터를 저장할 수 있는 필드(data)를 final로 선언
    /**
     * Map 사용 시, id 검색 O(1)
     * 하지만, List 사용 시, 검색된 순서가 유지 가능
     * Map의 경우: private final Map<UUID, User> users = new HashMap<>();
     * List의 경우: private final List<User> users = new ArrayList<>();
     */
    private final List<User> users = new ArrayList<>();

    /**
     * 생성자 주입, 외부에서 초기 데이터 주입 가능, List 참조를 공유해서 캡슐화 위반 가능 | `private data; constructor(data){this.data = data;}`
     * 내부 초기화, 내부 데이터 완전 캡슐화 | `private data = new(data); constructor(){}`
     * 방어적 복사, 외부 영향 없이 초기화 데이터 주입 가능, 테스트 때 사용 | `private data; constructor(data){this.data = new(data);}`
     * 현재: 내부 초기화(빈 생성자 메서드), create 메서드 존재로 User 내부 데이터 캡슐화 목적
     * 단점: Test 데이터 생성 불가
     */
    public JCFUserService() {}

    // Create
    @Override
    public User create(String username, String password, String email, String status, String role) {
        User newUser = new User(username, password, email, status, role);
        users.add(newUser);
        return newUser;
    }

    // Read
    @Override
    public User findById(UUID id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;  // fail-fast는 찾는 과정에서는 쓰이지 않는다.
        /* 간결한 Stream API
        return users.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst()
                    .orElse(null);
         */
    }

    @Override
    public User findByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users); // 방어적 복사 사용 'new ArrayList<>()'
        // return users; 이 아닌 이유, JCFUserService 외부에서 수정 가능한 부분 때문에 고려 필요
    }

    @Override
    public List<User> findByRole(String role) {
        List<User> result = new ArrayList<>();                     // 새 리스트 생성, 방어적 복사와 동일

        for (User user : users) {
            if (user.getRole().equals(role)) {
                result.add(user);
            }
        }
        return result;
        /*
        return users.stream()
                    .filter(user -> user.getRole().equals(role))
                    .collect(Collectors.toList());                 <-- 새 리스트 생성
        */
    }

    @Override
    public List<User> findByStatus(String status) {
        List<User> result = new ArrayList<>();

        for (User user : users) {
            if (user.getStatus().equals(status)) {
                result.add(user);
            }
        }
        return result;
    }

    // Update
    @Override
    public User update(UUID id, String username, String password, String email, String status, String role) {
        User user = findById(id);
        if (user == null) { // 찾은 유저가 없는 유저라면,, 로직 수행 막아야죠
            return null;
        }
        user.updateProfile(username, password, email, status, role);
        return user;
    }

    // Delete
    @Override
    public void delete(UUID id) {
        // java 8+, ArrayList의 removeIf는 내부적으로 동기화 처리 및 반복과 삭제를 한 번에 처리하여 일관성 유지, Delete는 stream이 부적합
        users.removeIf(user -> user.getId().equals(id));
    }
}
