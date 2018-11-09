package springmvc.service;

import org.springframework.stereotype.Service;

/**
 * Created by suneee on 2018/11/9.
 */
@Service
public class IndexService {

    public String show() {
        return "我是Service层";
    }
}
