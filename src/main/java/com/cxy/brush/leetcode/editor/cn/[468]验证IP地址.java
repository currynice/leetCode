package com.cxy.brush.leetcode.editor.cn;
//编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址。
//
// 
// 如果是有效的 IPv4 地址，返回 "IPv4" ； 
// 如果是有效的 IPv6 地址，返回 "IPv6" ； 
// 如果不是上述类型的 IP 地址，返回 "Neither" 。 
// 
//
// IPv4 地址由十进制数和点来表示，每个地址包含 4 个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1； 
//
// 同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。 
//
// IPv6 地址由 8 组 16 进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如, 2001:0db8:85a3:0000:0
//000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以， 2001:db8:85
//a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。 
//
// 然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334
// 是无效的 IPv6 地址。 
//
// 同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的
//。 
//
// 
//
// 示例 1： 
//
// 输入：IP = "172.16.254.1"
//输出："IPv4"
//解释：有效的 IPv4 地址，返回 "IPv4"
// 
//
// 示例 2： 
//
// 输入：IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
//输出："IPv6"
//解释：有效的 IPv6 地址，返回 "IPv6"
// 
//
// 示例 3： 
//
// 输入：IP = "256.256.256.256"
//输出："Neither"
//解释：既不是 IPv4 地址，又不是 IPv6 地址
// 
//
// 示例 4： 
//
// 输入：IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
//输出："Neither"
// 
//
// 示例 5： 
//
// 输入：IP = "1e1.4.5.6"
//输出："Neither"
// 
//
// 
//
// 提示： 
//
// 
// IP 仅由英文字母，数字，字符 '.' 和 ':' 组成。 
// 
// Related Topics 字符串 
// 👍 85 👎 0




//leetcode submit region begin(Prohibit modification and deletion)
import java.util.regex.Pattern;
class Solution {

    /**
     * InetAddress.getNameByIp()是引用 POSIX -兼容的 inet-addr() 解析地址。
     * 如果地址带有前导零块，可能会发生错误。
     *
     * 地址的组成可以使十进制，八进制（以 0 开始），或十六进制（以 0X 开始）。
     *
     *  01.01.01.012 也是有效的八进制 IP 地址，因为 ping 01.01.01.012 会被转换为十进制地址 1.1.1.10。
     *
     * 但本题指出如果 IPv4 地址包含前置 0视为无效 ，显然严格了许多。

     * @param IP
     * @return
     */
//    public String validIPAddress(String IP) {
//        try {
//            return (InetAddress.getByName(IP) instanceof Inet6Address) ? "IPv6": "IPv4";
//        } catch(Exception e) {}
//        return "Neither";
//    }

    /**
     * 正则表达式
     * IPV4:
     * 检查每个块是否正确，每个块的范围为 (0, 255)，不允许有前置零。一共有五种情况：
     *
     * 块只包含一位数字，范围是 0 到 9。
     *
     * 块包含两位数字，第一位的范围是 1 到 9，第二位是 0 到 9。
     *
     * 块包含三位数字，且第一位为 1。第二、三位都是 0 到 9。
     *
     * 块包含三位数字，且第一位为 2，第二位为 0 到 4。那么第三位可以是 0 到 9。
     *
     * 块包含三位数字，且第一位为 2，第二位为 5，那么第三位可以是 0 到 5。
     *
     *
     * @param IP
     * @return
     */


    public  String validIPAddress(String IP) {

        String chunkIPv4 = "([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";

        // ^:match begin of string
        // &:match end of string
        //
        Pattern pattenIPv4 =
                Pattern.compile("^(" + chunkIPv4 + "\\.){3}" + chunkIPv4 + "$");

        String chunkIPv6 = "([0-9a-fA-F]{1,4})";
        Pattern pattenIPv6 =
                Pattern.compile("^(" + chunkIPv6 + "\\:){7}" + chunkIPv6 + "$");
        if (IP.contains(".")) {
            return (pattenIPv4.matcher(IP).matches()) ? "IPv4" : "Neither";
        }
        else if (IP.contains(":")) {
            return (pattenIPv6.matcher(IP).matches()) ? "IPv6" : "Neither";
        }
        return "Neither";
    }




}
//leetcode submit region end(Prohibit modification and deletion)
