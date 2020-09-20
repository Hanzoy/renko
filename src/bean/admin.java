package bean;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import utils.DESUtils;
import utils.Utils;

import java.io.IOException;
import java.util.Base64;

import static utils.DESUtils.des3DecodeECB;

public class admin {
    private String name;
    private String password;

    @Override
    public String toString() {
        return "admin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public admin(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public admin() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取该对象的实体接口
     * @return
     */
    public String getCipherText(){
        try {
            byte[] key = new BASE64Decoder().decodeBuffer("9876543210abcdefghij1234567890d4");
            String str1 = Utils.encryptAgain(new BASE64Encoder().encode(DESUtils.des3EncodeECB(key, name.getBytes("UTF-8"))));
            String str2 = Utils.encryptAgain(new BASE64Encoder().encode( DESUtils.des3EncodeECB(key,password.getBytes("UTF-8"))));
            return str1+"*"+str2;
        } catch (IOException e) {
            System.out.println("密钥不存在");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 通过密文生成实体对象
     * @param cipherText
     * @return
     */
    public static admin cipherTextToUser(String cipherText){
        StringBuilder strB1= new StringBuilder();
        StringBuilder strB2= new StringBuilder();
        boolean flag = true;
        for(int i=0; i<cipherText.length(); i++){
            if(cipherText.charAt(i)!='*'&&flag){
                strB1.append(cipherText.charAt(i));
            }else if(cipherText.charAt(i)=='*'){
                flag=false;
            }else {
                strB2.append(cipherText.charAt(i));
            }
        }
        String str1 = Utils.decryptAgain(strB1.toString());
        String str2 = Utils.decryptAgain(strB2.toString());
        try {
            byte[] key = new BASE64Decoder().decodeBuffer("9876543210abcdefghij1234567890d4");
            byte[] str3 = des3DecodeECB(key, Base64.getDecoder().decode(str1));
            byte[] str4 = des3DecodeECB(key,Base64.getDecoder().decode(str2));
            return new admin(new String(str3,"UTF-8"),new String(str4,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
