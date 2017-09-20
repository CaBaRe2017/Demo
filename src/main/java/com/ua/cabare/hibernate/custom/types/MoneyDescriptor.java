package com.ua.cabare.hibernate.custom.types;

import com.ua.cabare.domain.Money;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.LongType;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class MoneyDescriptor implements UserType {

  @Override
  public int[] sqlTypes() {
    return new int[]{LongType.INSTANCE.sqlType()};
  }

  @Override
  public Class returnedClass() {
    return Money.class;
  }

  @Override
  public boolean equals(Object o, Object o1) throws HibernateException {
    return Objects.equals(o, o1);
  }

  @Override
  public int hashCode(Object o) throws HibernateException {
    return o.hashCode();
  }

  @Override
  public Object nullSafeGet(ResultSet resultSet, String[] strings,
      SharedSessionContractImplementor sharedSessionContractImplementor, Object o)
      throws HibernateException, SQLException {
    long aLong = resultSet.getLong(strings[0]);
    return Money.valueOf(aLong);
  }

  @Override
  public void nullSafeSet(PreparedStatement preparedStatement, Object o, int i,
      SharedSessionContractImplementor sharedSessionContractImplementor)
      throws HibernateException, SQLException {
    if (o == null) {
      preparedStatement.setLong(i, 0);
    } else {
      preparedStatement.setLong(i, ((Money) o).getValue());
    }
  }

  @Override
  public Object deepCopy(Object o) throws HibernateException {
    return o;
  }

  @Override
  public boolean isMutable() {
    return false;
  }

  @Override
  public Serializable disassemble(Object o) throws HibernateException {
    return ((Serializable) o);
  }

  @Override
  public Object assemble(Serializable serializable, Object o) throws HibernateException {
    return serializable;
  }

  @Override
  public Object replace(Object o, Object o1, Object o2) throws HibernateException {
    return o;
  }
}
