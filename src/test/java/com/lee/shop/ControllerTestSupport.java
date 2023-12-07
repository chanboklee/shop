package com.lee.shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lee.shop.api.controller.member.MemberController;
import com.lee.shop.api.controller.order.OrderController;
import com.lee.shop.api.controller.product.ProductController;
import com.lee.shop.api.service.member.MemberService;
import com.lee.shop.api.service.order.OrderService;
import com.lee.shop.api.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@WebMvcTest(controllers = {
        MemberController.class,
        OrderController.class,
        ProductController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected MemberService memberService;

    @MockBean
    protected OrderService orderService;

    @MockBean
    protected ProductService productService;
}
