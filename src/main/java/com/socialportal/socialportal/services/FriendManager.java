package com.socialportal.socialportal.services;

import com.socialportal.socialportal.models.Friend;
import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.repositories.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class FriendManager implements IFriendManager {

    private FriendRepository friendRepository;
    private IInvitationManager invitationManager;
    private IUserManager userManager;

    @Autowired
    public FriendManager(FriendRepository friendRepository, IInvitationManager invitationManager, IUserManager userManager) {
        this.friendRepository = friendRepository;
        this.invitationManager = invitationManager;
        this.userManager = userManager;
    }

    public List<Friend> getFriendsOfUser(Long id) {
        return friendRepository.getFriendsByUserId(id);
    }

    public List<User> getUsersFromFriendsOfUser(Long id) {
        List<Friend> friends = getFriendsOfUser(id);
        List<User> users = new LinkedList<>();
        for (Friend friend : friends) {
            users.add(friend.getFriend());
        }

        return users;
    }

    public void addFriend(Long loggedUserid, User user, Long invitationId) {
        Friend friend = new Friend();
        friend.setUserId(loggedUserid);
        friend.setFriend(user);
        friendRepository.save(friend);

        friend = new Friend();
        friend.setUserId(user.getId());
        friend.setFriend(userManager.getUserById(loggedUserid));
        friendRepository.save(friend);

        invitationManager.deleteInvitation(invitationId);
    }

    @Override
    public void deleteFriend(Long loggedUserid, User user) {
        Friend friend = friendRepository.getFriendByUserIdAndFriend(loggedUserid, user);
        friendRepository.delete(friend);
        friend = friendRepository.getFriendByUserIdAndFriend(user.getId(), userManager.getUserById(loggedUserid));
        friendRepository.delete(friend);
    }
}
