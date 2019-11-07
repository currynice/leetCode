package com.cxy.leetCode.stack;
/**
 * 使用前后栈实现浏览器的前进后退。
 *
 * @author chinalwb
 */
public class Browser {

    public static void main(String[] args) {
        Browser browser = new Browser();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com/");
        browser.open("http://news.baidu.com/ent");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("http://www.qq.com");
        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.goBack();
        browser.checkCurrentPage();
    }

    //当前页
    private String currentPage;
    //后退
    private LinkedListBaseStack backStack;
    //前进
    private LinkedListBaseStack forwardStack;

    //construction
    public Browser() {
        this.backStack = new LinkedListBaseStack();
        this.forwardStack = new LinkedListBaseStack();
    }

    //打开新页面
    public void open(String url) {
        if (this.currentPage != null) {
            //回退增加当前
            this.backStack.instack(this.currentPage);
            //旧的前进无法访问，清空
            this.forwardStack.clear();
        }
        showUrl(url, "Open");
    }

    public boolean canGoBack() {
        return this.backStack.getSize() > 0;
    }

    public boolean canGoForward() {
        return this.forwardStack.getSize() > 0;
    }

    public String goBack() {
        if (this.canGoBack()) {
            this.forwardStack.instack(this.currentPage);
            String backUrl = this.backStack.outstack();
            showUrl(backUrl, "Back");
            return backUrl;
        }

        System.out.println("* Cannot go back, no pages behind.");
        return null;
    }

    public String goForward() {
        if (this.canGoForward()) {
            this.backStack.instack(this.currentPage);
            String forwardUrl = this.forwardStack.outstack();
            showUrl(forwardUrl, "Foward");
            return forwardUrl;
        }

        System.out.println("** Cannot go forward, no pages ahead.");
        return null;
    }

    public void showUrl(String url, String prefix) {
        this.currentPage = url;
        System.out.println(prefix + " page == " + url);
    }

    public void checkCurrentPage() {
        System.out.println("Current page is: " + this.currentPage);
    }


}
