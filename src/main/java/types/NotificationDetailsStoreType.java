package types;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record NotificationDetailsStoreType(
        String NOTN,
        String NOTN_TYPE,
        String NOTN_DT,
        String NOTN_CAT,
        String PORT,
        String QUOTA_REQ,
        String CNTRY,
        String SLNO,
        String SUB_SLNO,
        String LIST_ITEM,
        String CTH,
        String ITEM_DESC,
        String RTA,
        int AMTS,
        String UQC,
        String FLG,
        String COND,
        String CVD_RTA,
        String CVD_AMTS,
        String CVD_UQC,
        String CVD_FLG,
        String AMND_REF,
        String MEMORAND,
        String CONDIT,
        String NOTN_ENDT,
        String A_NOTN,
        String A_NOTN_DT,
        String A_SLNO,
        String STATUS,
        String AD_FLG,
        String AMEND_BY,
        String AMEND_DT,
        String ENTRY_BY,
        String ENTRY_DT,
        String PFLG,
        String BCD_AMTS3,
        String BCD_UQC3,
        String BOND_CD,
        String SCH_CD,
        String DBK_TYPE,
        String SBMT_BY,
        String SBMT_DT,
        String NOTN_IDT,
        String ANTI_DUMP,
        String CVD_9

) {
}
