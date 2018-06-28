package org.mybatis.generator;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.xml.sax.InputSource;

/**
 * 描述：
 * @since 1.9.10
 * @version 1.9.10
 * @作者：niexiaohui
 * @创建时间：2016年11月22日
 * @修改记录：
 */
public class StartUp {
    public static void main(String[] args) throws URISyntaxException {
        try {
        	//args = new String[] {"F:\\java\\gencoder\\test.xml"};
        	if (args.length==0) {
        		System.out.println("配置文件不能为空");
        		System.exit(0);
        	}
        	String fileName = args[0];
        	File file = new File(fileName); 
        	if(!file.exists()) {
        		System.out.println("文件不存在");
        		System.exit(0);
        	}
        	FileReader fr = new FileReader(file);
        	InputSource inputSource = new InputSource(fr);
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            /*ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream is = classloader.getResourceAsStream(fileName);
            InputStream is = inputSource.getByteStream();*/
            
            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(file);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            System.out.println("开始执行。。。。。。。。。。。。。。。。。。。。。。。。。。。");
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
            System.out.println("生成文件结束。。。。。。。。。。。。。。。。。。。。。。。。。。。");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (XMLParserException e) {
            e.printStackTrace();
        }
    }
    
   
}