package com.lee.shop.api.service.order;

import com.lee.shop.api.controller.order.request.OrderProductRequest;
import com.lee.shop.api.service.member.MemberService;
import com.lee.shop.api.service.order.request.OrderCreateServiceRequest;
import com.lee.shop.api.service.order.response.OrderResponse;
import com.lee.shop.domain.member.Member;
import com.lee.shop.domain.member.MemberRepository;
import com.lee.shop.domain.order.Order;
import com.lee.shop.domain.order.OrderRepository;
import com.lee.shop.domain.orderproduct.OrderProduct;
import com.lee.shop.domain.product.Product;
import com.lee.shop.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public OrderResponse saveOrder(Long memberId, OrderCreateServiceRequest orderCreateServiceRequest){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        List<OrderProduct> orderProductList = new ArrayList<>();
        List<OrderProductRequest> orderProductRequestList = orderCreateServiceRequest.getOrderProductRequests();
        for(OrderProductRequest orderProductRequest : orderProductRequestList){
            Product product = productRepository.findByProductNumber(orderProductRequest.getProductNumber());
            int price = orderProductRequest.getPrice();
            if(price != orderProductRequest.getPrice()){
                price = orderProductRequest.getQuantity() * product.getPrice();
            }
            OrderProduct orderProduct = OrderProduct.createOrderProduct(price, orderProductRequest.getQuantity(), product);
            orderProductList.add(orderProduct);
        }

        Order order = Order.createOrder(member, orderProductList);
        Order savedOrder = orderRepository.save(order);

        return OrderResponse.of(savedOrder);
    }
}
