package com.ftel.isc;

import com.ftel.isc.testcase.TestCase1.TestCase1;
import com.ftel.isc.utils.Language;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    JLabel _case1, _case2, _case3;
    JButton _openWeb, _btnCase1, _btnCase2, _btnCase3;
    private static WebDriver driver;
    public Main(){
        //khởi tạo
        _case1 = new JLabel(Language.TEST_CASE_1);
        _case2 = new JLabel(Language.TEST_CASE_2);
        _case3 = new JLabel(Language.TEST_CASE_3);
        _openWeb = new JButton(Language.OPEN_WEBSITE);
        _btnCase1 = new JButton(Language.PERFORM);
        _btnCase2 = new JButton(Language.PERFORM);
        _btnCase3 = new JButton(Language.PERFORM);
        //set kích thước
        _openWeb.setBounds(0, 10, 300, 20);
        _case1.setBounds(50, 50, 250, 20);
        _case2.setBounds(50, 100, 250, 20);
        _case3.setBounds(50, 150, 250, 20);
        _btnCase1.setBounds(150, 50, 90, 20);
        _btnCase2.setBounds(150, 100, 90, 20);
        _btnCase3.setBounds(150, 150, 90, 20);
        //thêm vào màn hình
        add(_case1);
        add(_case2);
        add(_case3);
        add(_openWeb);
        add(_btnCase1);
        add(_btnCase2);
        add(_btnCase3);
        //
        _openWeb.addActionListener(this);
        _btnCase1.addActionListener(this);
        _btnCase2.addActionListener(this);
        _btnCase3.addActionListener(this);
        setTitle(Language.TITLE_TOOL);
        setSize(300, 300);
        setLayout(null);
        setVisible(true);
    }

    public void OpenWeb(){
        System.setProperty("webdriver.chrome.driver", "/Users/nhanne/Downloads/ToolTest/src/main/java/com/ftel/isc/chromedriver");
        driver = new ChromeDriver();
        driver.get(Language.BASE_URL);
        driver.manage().window().maximize();
    }

    public static void main(String[] args) {
        new Main();
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource() == _openWeb){
                OpenWeb();
            }
            else if(e.getSource() == _btnCase1){
               TestCase1 case1 = new TestCase1();
               case1.actionTest();
            }
            else if(e.getSource() == _btnCase2){

            }
            else{

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
