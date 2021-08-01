package models;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * 日報データのDTOモデル
 */

@Table(name = JpaConst.TABLE_REACT)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_REACT_GET_ALL,
            query = JpaConst.Q_REACT_GET_ALL_DEF),
    @NamedQuery(
            name = JpaConst.Q_REACT_COUNT,
            query = JpaConst.Q_REACT_COUNT_DEF),
    @NamedQuery(
           name = JpaConst.Q_REACT_COUNT_ALL_REACT,
           query = JpaConst.Q_REACT_COUNT_ALL_REACT_DEF),

    @NamedQuery(
            name = JpaConst.Q_REACT_COUNT_ALL_LOGIN_REACT,
            query = JpaConst.Q_REACT_COUNT_ALL_LOGIN_REACT_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Reaction {

    /*
     * id
     */
    @Id
    @Column(name = JpaConst.REACT_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     * リアクションをした従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.REACT_COL_EMP_ID,nullable = false)
    private Employee employee;

    /*
     * リアクションをされた日報
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.REACT_COL_REP_ID,nullable = false)
    private Report report;

    /*
     * リアクションの種類
     */

    @Column(name = JpaConst.REACT_COL_REACT_TYPE,nullable = false)
    private Integer reactionType;


    /*
     * 登録日時
     */
    @Column(name = JpaConst.REP_COL_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;


}
