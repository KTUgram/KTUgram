package com.KTUgrammeriai.KTUgram_backend.blockedUsers;

import com.KTUgrammeriai.KTUgram_backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlockedUsersService {

    @Autowired
    public BlockedUsersRepository blockedUsersRepository;


    @Transactional
    public void saveBlockedUsers(BlockedUsers blockedUsers){
        blockedUsersRepository.save(blockedUsers);
    }

    @Transactional
    public void deleteBlockedUser(BlockedUsers blockedUsers){
        blockedUsersRepository.delete(blockedUsers);
    }

    public List<BlockedUsers> getAllBlockedUsers(){
        return (List<BlockedUsers>) blockedUsersRepository.findAll();
    }
}
