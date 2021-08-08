package models;

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

@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_ROLE_DATA,
            query = JpaConst.Q_ROLE_DATA_DEF),

})

@Table(name = JpaConst.TABLE_ROLE)


@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドに引数を持つ引数ありコンストラクタを自動生成する(Lombok)
@Entity //
public class Role {
    /*
     * ID
     */
    @Id
    @Column(name = JpaConst.ROLE_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     * 社員番号(社員情報）
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.ROLE_EMP_ID,nullable = false)
    private Employee employee;

    /*
     * 所属部署のステータス
     */
    @Column(name = JpaConst.DEPARTMENT_GR,nullable = false)
    private Integer departmentGr;

    /*
     * 承認者権限があるかどうか(なし：０、あり：１）
     */
    @Column(name = JpaConst.ROLE_FLAG,nullable = false)
    private Integer roleFlag;






}
