package top.fols.box.net;

public class XURLParamTest {
    public static void main(String[] args) throws Throwable {
        XURLParam xurlp = new XURLParam("from=1012852s&%E8%94%A1%E5%BE%90%E5%9D%A4=%E8%A1%8C%E4%B8%BA");
        System.out.println(xurlp.get("为什么"));
        xurlp.clear();
        xurlp.put("你", "这么骚");
        xurlp.put("??", "esm");
        System.out.println(xurlp);
        System.out.println(xurlp.toFormatString());
        System.out.println(xurlp.get("你"));
        /*
         * null {%3F%3F=esm, %E4%BD%A0=%E8%BF%99%E4%B9%88%E9%AA%9A}
         * ?%3F%3F=esm&%E4%BD%A0=%E8%BF%99%E4%B9%88%E9%AA%9A 这么骚
         */
    }
}