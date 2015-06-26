package com.rigil.fda.base;

import com.rigil.fda.config.WebMvcContextConfig;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.rigil.fda.config.JunitBaseJpaConfig;
import com.rigil.fda.config.JunitDataSourceConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
    @ContextConfiguration(classes={
            JunitDataSourceConfig.class,
            JunitBaseJpaConfig.class }),
    @ContextConfiguration(classes={WebMvcContextConfig.class})
})
@WebAppConfiguration("src/main/webapp")
@ActiveProfiles(profiles={"unitTest"})
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@DatabaseSetup(value={
        "classpath:schema/HibernateIdGenerationData.xml"
    })
public class AbstractBaseTest {

    public static final String email = "ravi@rigil.com";
    public static final String phone = "703-473-0117";

}
