package org.cdz.iservice;

import java.util.List;
import java.util.Map;

import com.cdz.User;

public interface LoginService {

	List<User> list(Map<String,Object> map);

}
