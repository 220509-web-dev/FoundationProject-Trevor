package services;

import dao.UserDBDAO;
import dto.ResourceCreationResponse;
import entities.User;
import utils.CustomLogger;
import utils.exceptions.UnavailableUsernameException;

import java.util.List;

public class UserService {
    private final UserDBDAO dbDao;

    public UserService(UserDBDAO dbDao) {
        this.dbDao = dbDao;
    }

    public User getByUsername(String username) {
        try {
            if (username.length() < 4) {
                throw new UnavailableUsernameException("Username has to be greater than four");

            } else if (username == null) {
                throw new UnavailableUsernameException("there is no username provided");

            } else  {
                return dbDao.getByUsername(username);

            }
        } catch (Exception e) {
            CustomLogger.writeToLog(e.toString());
            e.printStackTrace();
        }
        return null;
    }

    public User getById(String id) {
        try {
            int idNumber = Integer.parseInt(id);

            if (idNumber < 0) {
                throw new UnavailableUsernameException("Id has to be greater than 0");

            } else if (idNumber == Integer.parseInt(null)) {
                throw new UnavailableUsernameException("there is no Id provided");

            } else {
                return dbDao.getById(idNumber);
            }
        }catch (Exception e) {
            CustomLogger.writeToLog(e.toString());
            e.printStackTrace();
        }

        return null;
    }

    public List<User> getAllUsers() {
        return dbDao.getAllUsers();
    }

    public ResourceCreationResponse createNewUser(User newUser) {
        return new ResourceCreationResponse(dbDao.create(newUser).getId());
    }
}
