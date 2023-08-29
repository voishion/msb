package #(packageConfig.servicePackage);

import #(serviceConfig.buildSuperClassImport());
import #(packageConfig.entityPackage).#(table.buildEntityClassName());

/**
 * #(table.getComment()) 服务接口
 *
 * @author #(javadocConfig.getAuthor())
 * @since #(javadocConfig.getSince())
 */
public interface #(table.buildServiceClassName()) extends #(serviceConfig.buildSuperClassName())<#(table.buildEntityClassName())> {

}
