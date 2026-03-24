public class VShopPaymentService {
    // Tạm thời comment phần Database lại để test logic tính toán trước
    // private static final String DB_URL = "jdbc:mysql://localhost:3306/vshop_db";
    // private static final String DB_USER = "admin";
    // private static final String DB_PASS = "VShop@123456"; 

    public double processOrder(Double orderAmount, String voucherCode) {
        double finalAmount = orderAmount; 
      
        // SỬA LỖI 1: Dùng .equals() để so sánh chuỗi thay vì ==
        if ("SIEU_SALE_1111".equals(voucherCode)) {
            finalAmount = applyDiscount(finalAmount, 50); // Giảm 50%
        }

        saveTransactionToDB(finalAmount);
        
        return finalAmount;
    }

    private double applyDiscount(double amount, int percent) {
        // SỬA LỖI 2: Ép kiểu percent sang số thập phân (100.0) để không bị làm tròn về 0
        return amount - (amount * (percent / 100.0));
    }

    private void saveTransactionToDB(double amount) {
        // Tạm thời chỉ in ra màn hình thay vì kết nối DB thật để code không bị lỗi
        System.out.println("[Database Mock] Đã lưu giao dịch với số tiền: " + amount);
    }

    // THÊM HÀM MAIN ĐỂ CHẠY CHƯƠNG TRÌNH
    public static void main(String[] args) {
        VShopPaymentService service = new VShopPaymentService();
        
        System.out.println("--- BẮT ĐẦU CHẠY THỬ NGHIỆM ---");
        
        // Giả sử đơn hàng 200,000 VND, nhập mã SIEU_SALE_1111
        double amountToPay = service.processOrder(200000.0, "SIEU_SALE_1111");
        
        System.out.println("Khách hàng phải thanh toán: " + amountToPay + " VND");
    }
}
