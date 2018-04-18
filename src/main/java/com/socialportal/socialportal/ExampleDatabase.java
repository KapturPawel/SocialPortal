package com.socialportal.socialportal;

import com.socialportal.socialportal.models.User;
import com.socialportal.socialportal.models.UserComment;
import com.socialportal.socialportal.models.UserStatus;
import com.socialportal.socialportal.services.ICommentManager;
import com.socialportal.socialportal.services.IStatusManager;
import com.socialportal.socialportal.services.IUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;

@Component
public class ExampleDatabase implements CommandLineRunner {

    @Autowired
    IUserManager userManager;

    @Autowired
    IStatusManager statusManager;

    @Autowired
    ICommentManager commentManager;

    @Override
    public void run(String... args) throws Exception {
        LinkedList<User> users = new LinkedList<>();
        users.add(new User("Jan", "Kox", "qwerty", "prosty@gmail.com"));
        users.add(new User("Piotr", "Prox", "qwerty", "prosty1@gmail.com"));
        users.add(new User("Magda", "Jet", "qwerty", "prosty2@gmail.com"));
        users.add(new User("Jan", "Niezbedny", "qwerty", "prosty3@gmail.com"));
        users.add( new User("Jan", "Zbedny", "qwerty", "prosty4@gmail.com"));
        users.add( new User("Piotr", "Kox", "qwerty", "prosty5@gmail.com"));

        for(User user : users){
            userManager.registerUser(user);
        }

        LinkedList<UserStatus> statuses = new LinkedList<>();

        statuses.add(new UserStatus("First status",  1L, new Date(), users.get(0)));
        statuses.add(new UserStatus("Second status",  1L, new Date(), users.get(0)));
        statuses.add(new UserStatus("Third status",  2L, new Date(), users.get(0)));
        statuses.add(new UserStatus("Fourth status",  2L, new Date(), users.get(1)));
        statuses.add(new UserStatus("Fifth status",  2L, new Date(), users.get(2)));
        statuses.add(new UserStatus("Sixth status",  3L, new Date(), users.get(1)));
        statuses.add(new UserStatus("Seventh status",  3L, new Date(), users.get(2)));

        for(UserStatus status: statuses){
            statusManager.addNewStatus(status);
        }

        LinkedList<UserComment> comments = new LinkedList<>();

        comments.add(new UserComment("First Comment", new Date(), 1L, statuses.get(0), users.get(0)));
        comments.add(new UserComment("Second Comment", new Date(), 1L, statuses.get(0), users.get(0)));
        comments.add(new UserComment("Third Comment", new Date(), 1L, statuses.get(0), users.get(0)));
        comments.add(new UserComment("Fourth Comment", new Date(), 1L, statuses.get(0), users.get(0)));

        for(UserComment comment: comments){
            commentManager.addNewComment(comment);
        }
    }
}
