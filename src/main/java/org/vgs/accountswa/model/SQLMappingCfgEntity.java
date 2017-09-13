package org.vgs.accountswa.model;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@SqlResultSetMapping(name = "ChartResults", classes = {
		@ConstructorResult(targetClass = ChartDataModel.class, columns = {
				@ColumnResult(name = "label", type = String.class),
				@ColumnResult(name = "value", type = Double.class) }) })
@Entity
public class SQLMappingCfgEntity {
	@Id
	int id;
}