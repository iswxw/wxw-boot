package com.wxw.test.controller;

import com.wxw.controller.RestDemoController;
import com.wxw.domain.FootprintResponseVO;
import com.wxw.domain.UserQuizStatDTO;
import com.wxw.service.RestDemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author weixiaowei
 * @desc:
 * @date: 2021/4/28
 */

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class ControllerJunit {

    private static final String AUTHORIZATION = "Authorization";

    private MockMvc mvc;

    @Mock
    private RestDemoService toolsService;

    @InjectMocks
    private RestDemoController controller;

    /**
     * post
     *
     * @throws Exception
     */
    @Test
    public void test_info() throws Exception {
        Mockito.when(toolsService.fetchUserId("18810602860")).thenReturn("1537497");
        String RequestParams = "{\"endTime\":\"2021-04-28 17:02:00\",\"eventId\":\"\",\"mobile\":\"15910253237\",\"pager\":{\"cursor\":\"\",\"pageSize\":20},\"startTime\":\"2021-04-28 00:00:00\",\"userNumber\":\"\"}";
        Date date1 = Mockito.mock(Date.class);
        Date date2 = Mockito.mock(Date.class);
        Mockito.when(toolsService.getprint("1537497",
                date1, date2, "", false, false))
                .thenReturn(Mockito.mock(FootprintResponseVO.class));
        mvc.perform(MockMvcRequestBuilders.post("/footprint/info")
                .header(AUTHORIZATION, "2")
                .contentType(MediaType.APPLICATION_JSON)
                .content(RequestParams))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"code\":0,\"errorInfo\":\"\",\"msg\":\"\",\"status\":0}"));
    }

    /**
     * get
     *
     * @throws Exception
     */
    @Test
    public void test_interactiveQuizStat() throws Exception {
        List<UserQuizStatDTO> quizStatDTOList = Mockito.mock(List.class);
        LocalDate localDate = Mockito.mock(LocalDate.class);
        Mockito.when(toolsService.userQuizStats("18810602860", "1537497", localDate, "")).thenAnswer(new Answer<List<UserQuizStatDTO>>() {
            @Override
            public List<UserQuizStatDTO> answer(InvocationOnMock invocationOnMock) throws Throwable {
                return quizStatDTOList;
            }
        });
        mvc.perform(MockMvcRequestBuilders.get("/footprint/interactive_quiz_stat").contentType(MediaType.APPLICATION_JSON)
                .param("mobile", "18810602860")
                .param("userId", "1537497")
                .param("lessonId", "")
                .param("date", "2021-04-28"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"status\":0,\"errorInfo\":\"\",\"code\":0,\"msg\":\"\",\"data\":[]}"));
    }


    @Before
    public void setup() {
        //初始化
        MockitoAnnotations.initMocks(this);
        //构建mvc环境
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
}
