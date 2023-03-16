package users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import users.constants.Constants;
import users.model.User;
import users.service.UserService;

import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @GetMapping(value="users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> users(@RequestParam(defaultValue = "0.0", required = false) Float min,
                                   @RequestParam(defaultValue = "4000.0",required = false) Float max,
                                   @RequestParam(defaultValue = "0",required = false) Integer offset,
                                   @RequestParam(required = false) Integer limit,
                                   @RequestParam(required = false) String sort) {
        try {
            List<User> users = userService.getUsers(min,max,offset,limit,sort);
            HashMap<String, Object> results = new HashMap<>();
            results.put("results", users);
            return ResponseEntity.status(HttpStatus.OK).body(results);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(Constants.ERROR_MESSAGE + e.getMessage());
        }
    }
}
