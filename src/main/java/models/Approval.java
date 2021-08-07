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

@Table(name = JpaConst.TABLE_APPROV)

@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_APPROV_DATA,
            query = JpaConst.Q_APPROV_DATA_DEF)

    })



@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドに引数を持つ引数ありコンストラクタを自動生成する(Lombok)
@Entity //
public class Approval {
     /*
     * ID
     */
    @Id
    @Column(name = JpaConst.APPROV_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /*
     * 承認対象の日報ID（日報情報）
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.APPROV_REP_ID,nullable = false)
    private Report report;
    /*
     * 承認状態がどうか(未承認：０、承認：１）
     *
     *     @Column(name = JpaConst.APPROV_FLAG,nullable = false)
    private Integer approvalFlag;

     *
     */

    /*
     * 登録日時
     */
    @Column(name = JpaConst.APPROV_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    /*
     * 登録日時
     */
    @Column(name = JpaConst.APPROV_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;




}
