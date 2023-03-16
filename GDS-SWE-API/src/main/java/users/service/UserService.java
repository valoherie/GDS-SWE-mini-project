package users.service;

import users.util.IllegalArgException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import users.entity.UsersEntity;
import users.model.User;
import users.repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public List<User> getUsers(Float min, Float max, Integer offset, Integer limit, String sort) {
        if (limit == null) {
            limit = (int) usersRepository.count();
        }
        if (offset < 0) {
            throw new IllegalArgException("Offset index must not be negative");
        }
        if (limit < 1) {
            throw new IllegalArgException("Limit must not be negative");
        }
        if (min < 0) {
            throw new IllegalArgException("Minimum Salary must not be negative");
        }
        if (max < 0 || max > 4000) {
            throw new IllegalArgException("Maximum Salary must be between $0 and $4000");
        }
        Pageable paging;
        if (sort == null) {
            paging = PageRequest.of(0, limit);
        } else {
            paging = PageRequest.of(0, limit, Sort.by(sort.toString()).ascending());
        }

        Page<UsersEntity> userEntities = usersRepository.findAll(min, max, paging);
        List<User> users = new ArrayList<>();

        //offset - skipping no. of records based on offset param
        userEntities.stream().skip(offset).forEach(usersEntity -> {
           User user = new User();
           user.setName(usersEntity.getName());
           user.setSalary(usersEntity.getSalary());

           users.add(user);
        });

        return users;
    }
}
