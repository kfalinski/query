package core.tutorial;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Category.class)
public abstract class Category_ {

	public static volatile SingularAttribute<Category, String> name;
	public static volatile SingularAttribute<Category, Integer> categoryId;
	public static volatile SetAttribute<Category, Stock> stocks;
	public static volatile SingularAttribute<Category, String> desc;

}

