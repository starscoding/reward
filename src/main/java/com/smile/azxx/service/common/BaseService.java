package com.smile.azxx.service.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by smile on 2018/4/6.
 */
@Service
@Transactional(readOnly = true)
public class BaseService {

    protected Logger logger = LoggerFactory.getLogger(getClass());
}
