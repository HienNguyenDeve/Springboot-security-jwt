package com.nguyenhien.jwtsecurity.commons.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ValidationConstants {
    // 1. Số điện thoại (Cho phép trống HOẶC từ 10-15 số, có thể có dấu +)
    public static final String PHONE_REGEX = "^$|^\\+?[0-9]{10,15}$";

    // 2. Số điện thoại Việt Nam (Cho phép trống HOẶC bắt đầu bằng +84 hoặc 0, theo sau là các đầu số hợp lệ, và kết thúc bằng 7 chữ số)
    public static final String VN_PHONE_REGEX = "^(0|84)(3|5|7|8|9)[0-9]{8}$";

    // 3. Email (Định dạng email chuẩn)
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";

    // 4. Mật khẩu mạnh (Ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt)
    public static final String STRONG_PASS_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    // 5. Tên người dùng (Cho phép chữ cái, số, dấu chấm, gạch dưới và gạch ngang, từ 3 đến 50 ký tự)
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9._-]{3,50}$";
}
