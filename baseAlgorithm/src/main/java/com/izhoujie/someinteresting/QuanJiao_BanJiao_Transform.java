package com.izhoujie.someinteresting;

/**
 * @author admin@izhoujie.com
 * 
 *         2016年9月7日 12:16:15
 * 
 *         -实现对字符串中全角字符和半角字符的互相转换
 */
public class QuanJiao_BanJiao_Transform {

    // ASCII表中可见字符从!开始，值为33(Decimal),半角对应字符为 !
    static final char DBC_CHAR_START = 33;

    // ASCII表中可见字符到~结束，值为126(Decimal),半角对应字符为 ~
    static final char DBC_CHAR_END = 126;

    // 全角对应于ASCII表的可见字符从！开始，值为65281 ,全角对应字符为 ！
    static final char SBC_CHAR_START = 65281;

    // 全角对应于ASCII表的可见字符到～结束，值为65374,全角对应字符为 ～
    static final char SBC_CHAR_END = 65374;

    // ASCII表中除空格外的可见字符与对应的全角字符的相对偏移,全角半角转换偏移量为65248
    static final int CONVERT_STEP = 65248;

    // 全角空格的值，没有遵循上面说的ASCII的相对偏移量，必须单独处理,全角空格 ASCII值为12288
    static final char SBC_SPACE = 12288;

    // 半角空格的值，ASCII值为32(Decimal),半角空格 ' '
    static final char DBC_SPACE = 32;

    /**
     * 
     * @param src
     *            包含半角字符的字符串
     * @return 返回全角字符的字符串
     */
    public static String bj2qj(String src) {
	if (src == null) {
	    return src;
	}
	StringBuilder buf = new StringBuilder(src.length());
	char[] ca = src.toCharArray();
	for (int i = 0; i < ca.length; i++) {
	    // 如果是半角空格，直接用全角空格替代
	    if (ca[i] == DBC_SPACE) {
		buf.append(SBC_SPACE);
		// 字符是!到~之间的可见字符
	    } else if ((ca[i] >= DBC_CHAR_START) && (ca[i] <= DBC_CHAR_END)) {
		buf.append((char) (ca[i] + CONVERT_STEP));
		// 不对空格以及ascii表中其他可见字符之外的字符做任何处理
	    } else {
		buf.append(ca[i]);
	    }
	}
	return buf.toString();
    }

    /**
     * 
     * @param src
     *            包含全角字符的字符串
     * @return 返回半角字符的字符串
     */
    public static String qj2bj(String src) {
	if (src == null) {
	    return src;
	}
	StringBuilder buf = new StringBuilder(src.length());
	char[] ca = src.toCharArray();
	for (int i = 0; i < src.length(); i++) {
	    // 如果位于全角！到全角～区间内
	    if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END) {
		buf.append((char) (ca[i] - CONVERT_STEP));
		// 如果是全角空格
	    } else if (ca[i] == SBC_SPACE) {
		buf.append(DBC_SPACE);
		// 不处理全角空格，全角！到全角～区间外的字符
	    } else {
		buf.append(ca[i]);
	    }
	}
	return buf.toString();
    }

    // 测试
    public static void main(String[] args) {
	String qj = "ｋｍｓｏｃｉａｌ，周杰。．　－。－";
	String bj = "kmsocial,周杰. -.-";

	String qj2bj = QuanJiao_BanJiao_Transform.qj2bj(qj);
	String bj2qj = QuanJiao_BanJiao_Transform.bj2qj(bj);

	System.out.println("转换前:" + qj + "\n转换后:" + qj2bj);
	System.out.println("转换前:" + bj + "\n转换后:" + bj2qj);
    }
}