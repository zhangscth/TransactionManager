package com.zsc.transactionmanager.mapper;

import com.zsc.transactionmanager.entity.Transaction;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TransactionMapper {
    @Insert("INSERT INTO transactions (id, amount, type, description, timestamp) " +
            "VALUES (#{id}, #{amount}, #{type}, #{description}, #{timestamp})")
    int insert(Transaction transaction);

    @Delete("DELETE FROM transactions WHERE id = #{id}")
    int deleteById(String id);

    @Update("UPDATE transactions SET amount=#{amount}, type=#{type}, description=#{description} " +
            "WHERE id = #{id}")
    void update(Transaction transaction);

    @Select("SELECT id, amount, type, description, timestamp FROM transactions WHERE id = #{id}")
    Optional<Transaction> findById(String id);

    @Select("SELECT id, amount, type, description, timestamp FROM transactions ORDER BY timestamp DESC LIMIT #{size} OFFSET #{offset}")
    List<Transaction> findAll(@Param("offset") int offset, @Param("size") int size);
}