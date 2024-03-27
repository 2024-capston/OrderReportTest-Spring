## 버전 정보 
- Spring 3.2.0 이상
- MySQL 8.x 
- Java 17
- Querydsl-jpa:5.0.0

## Table Diagram 
![Screenshot 2024-03-27 at 9 59 57 PM](https://github.com/2024-capston/OrderReportTest-Spring/assets/79124461/dd86514f-7ad3-497f-8386-31ea0925334b)

## 목표 
- Test 를 위해 Mock 데이터 생성하는 API 를 생성한다.
- 사용자 id 를 통해 사용자의 주문 정보를 모두 가져오는 API 만든다.
- 순수 JPA 를 사용했을 때와 성능 개선을 한 쿼리를 사용했을 때의 성능 차이를 비교한다 (쿼리 생성을 위해 Querydsl 사용) 

## 사용 방법 및 설명 
### src/main/resources/application.yml 정보 수정
- datasoruce 의 정보를 사용자의 설정에 맞게 수정합니다.
- <> 로 표시한 영역만 수정하시면 됩니다. 

### Mock 데이터 생성
아래의 API 를 순서대로 실행합니다 
- GET /createUser
- GET /createProduct
- GET /createOrder
- GET /createOrderDetail
- GET /createShipment

생성 관련 기본 구현 정보는 다음과 같습니다. 
1. User, Product 는 1000개 생성합니다. (services/UserMockService.class, services/PrductMockService.class 참고) 
2. Order 는 사용자의 수만큼 생성합니다. (services/OrderMockService.class 참고) 
3. OrderDetail 은 User 100명 * Product 100개 정보로 총 10,000개 생성합니다. (services/OrderDetail.class 참고)
4. Shipment 은 Order 의 수 만큼 생성합니다 (services/ShipmentMockService.class 참고)

### OrderReport 조회

아래의 API 를 통해 조회할 수 있습니다.

- GET /order-report/no-query-dsl/{userid} - 순수 JPA 만 사용 
- GET /order-report/query-dsl/{userid} - query dsl 사용 

OrderReport 는 다음과 같은 정보를 담고 있습니다. 
1. 사용자의 PrimaryKey, 이름, 이메일
2. 주문정보 (ArrayList)
3. 배송정보 (ArrayList)

주문 정보는 다음과 같은 정보를 담고 있습니다. 
1. 주문 id (PrimaryKey)
2. 주문날짜
3. 주문 상태
4. 상품 정보 (ArrayList)

상품 정보는 다음과 같은 정보를 담고 있습니다. 
1. 상품 id (Primary Key)
2. 상품 이름
3. 가격
4. 주문한 양
5. 총 금액 

배송 정보는 다음과 같은 정보를 담고 있습니다. 
1. 배송 id (Primary Key) 
2. 배송 시작 날짜 
3. 배송 상태 

dto 폴더에서 위와 관련된 코드를 확인할 수 있습니다. 

### 결과

**a) 순수 JPA 사용**

<img width="337" alt="Screenshot 2024-03-27 at 7 12 55 PM" src="https://github.com/2024-capston/OrderReportTest-Spring/assets/79124461/7f83b5b4-74ff-4da3-9b3c-3260c8bbbd91">

<img width="338" alt="Screenshot 2024-03-27 at 7 13 03 PM" src="https://github.com/2024-capston/OrderReportTest-Spring/assets/79124461/d33c8308-d9d0-4e6d-9dfb-af79daa38728">

<img width="347" alt="Screenshot 2024-03-27 at 7 13 24 PM" src="https://github.com/2024-capston/OrderReportTest-Spring/assets/79124461/ef2d4495-4edf-4e68-9f3e-c4c4a27d6e33">

**b) Querydsl 사용** 

![Screenshot 2024-03-27 at 9 59 16 PM](https://github.com/2024-capston/OrderReportTest-Spring/assets/79124461/5f6bf852-089b-492d-8025-f9ea11c3cabb)

![Screenshot 2024-03-27 at 9 59 13 PM](https://github.com/2024-capston/OrderReportTest-Spring/assets/79124461/496729b3-406e-46ed-9f76-e596504cde8e)

![Screenshot 2024-03-27 at 9 59 10 PM](https://github.com/2024-capston/OrderReportTest-Spring/assets/79124461/55209185-fc1e-49b8-9038-5ddc57983515)

**5개 테이블 조인을 할 때 순수 JPA 를 사용했을 때와 성능 개선을 했을 때 약 40배 차이가 나는 것을 확인할 수 있다.** 
