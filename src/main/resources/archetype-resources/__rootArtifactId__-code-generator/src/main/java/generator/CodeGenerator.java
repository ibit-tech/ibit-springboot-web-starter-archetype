package ${package}.generator;

import org.apache.commons.lang.StringUtils;
import tech.ibit.mybatis.generator.Generator;
import tech.ibit.mybatis.generator.ProjectInfo;

/**
 * 代码生成对应的类
 *
 * @author IBIT TECH
 */
public class CodeGenerator {

    public static void main(String... args) {
        String driverName = "com.mysql.jdbc.Driver";

        // 设置jdbc路径
        String jdbcURL = "jdbc:mysql://localhost:3306/demo?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";
        String username = "**";
        String password = "**";

        // 指定表明
        String[] tables = new String[]{
                "user",
                "enterprise"
        };

        Generator generator = new Generator(driverName, jdbcURL, username, password, StringUtils.join(tables, ","));

        // 指定基本包路径
        String projectDir = "/xx/db";
        String basePackage = "${package}.db";
        generator.setDefaultProject(new ProjectInfo(projectDir, basePackage));

        // 指定作者
        generator.setAuthor("IBIT TECH");

        // 设置生成所有类型文件，Mapper, Dao, Entity
        generator.setWithAll();

        // 是否覆盖（false：文件已经存在，则不覆盖）
        generator.setOverride(true);

        // generator.setWithDao(); // 设置只生成Dao
        // generator.setWithEntity(); // 设置只生成Entity
        // generator.setWithMapper(); // 设置只生成Mapper文件

        // generator.setDaoProject(); // 可以单独指定Dao项目路径
        // generator.setEntityProject(); // 可以单独设置entity项目路径
        // generator.setMapperProject(); // 可以单独设置mapper项目路径

        generator.generateFiles();
    }
}
