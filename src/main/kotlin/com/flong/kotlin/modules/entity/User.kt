package com.flong.kotlin.modules.entity

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableLogic;
import java.io.Serializable
import java.util.Date
import com.alibaba.fastjson.annotation.JSONField
/**
 *data保持数据data class就是一个类中只包含一些数据字段，类似于vo,pojo,java bean。一般而言，
 *我们在Java中定义了这个数据类之后要重写一下toString，equals等方法。要生成get,set方法
 *https://www.cnblogs.com/liuliqianxiao/p/7152773.html
 *注意mybatis查询数据，然后封装实体的时候，构造方法这里有点儿坑，查询的字段必须与构造方法一直。
 */

@TableName("t_user")
data  class User constructor(
	@TableId(value = "user_id", type = IdType.ID_WORKER)
	var userId: Long?= null,//用户Id主键,IdWork生成
	var userName: String? = null,//用户名
	var passWord: String? = null,//密码
	@TableLogic //mybatisplus的逻辑删除标识
	//指定表字段进行绑定实体字段属性，is_deleted表示在表实在存在的字段.
	@TableField(value="is_deleted")
	//转换成跟数据库表一样的属性字段
	@field:JSONField(name="isDeleted")
	var delFlag: String? = null,//删除
	var createTime: Date? = null //创建时间,允许为空,让数据库自动生成即可
  ) :Serializable{
 
	//手动重写toString方法
	override fun toString(): String {
		return "[User(userId = $userId,userName = $userName, passWord=$passWord,isDeleted=$delFlag,createTime=$createTime)]"
	}

	//equals
	override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        if (userId != other.userId) return false
        if (userName != other.userName) return false
		if (passWord != other.passWord) return false
		if (delFlag != other.delFlag) return false
		if (createTime != other.createTime) return false
        return true
    }
	override fun hashCode(): Int {
		
        var result = userId?.hashCode() ?: 0
        result = 31 * result + (userName?.hashCode() ?: 0)
        result = 31 * result + (passWord?.hashCode() ?: 0)
        result = 31 * result + (delFlag?.hashCode() ?: 0)
        result = 31 * result + (createTime?.hashCode() ?: 0)
        
        return result
    }

}

