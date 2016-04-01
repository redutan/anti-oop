package io.redutan.antioop.noif;

/**
 * 결제 서비스 인터페이스
 * @author myeongju.jung
 */
public interface PaymentService {
	// 실시간 할인내역 확인
	Discount getDiscount(DiscountRequest request);

	// 결제처리
	void payment(PaymentRequest request);
}
