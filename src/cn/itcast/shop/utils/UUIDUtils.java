package cn.itcast.shop.utils;

import java.util.UUID;

//��������ַ����Ĺ�����
public class UUIDUtils {
public static String getUUID(){
	return UUID.randomUUID().toString().replace("-", "");
}
}
