package com.yech.yechblog.action;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * 抽象 Action，用于继承
 * @author Administrator
 */
public abstract class BaseAction<T> extends ActionSupport 
							implements ModelDriven<T>,Preparable {

	private static final long serialVersionUID = 1L;
	
	public T model;
	
	/**
	 * 父类中 利用反射初始化 public 类型的 model，在子类中就可以省去 getModel()方法了
	 */
	@SuppressWarnings("unchecked")
	public BaseAction() {
		ParameterizedType type = 
				(ParameterizedType) this.getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void prepare() throws Exception {
		
	}
	
	@Override
	public T getModel(){
		return model;
	}
}
