package cn.edu.zucc.wyk31801026.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author wangyangkai
 * @since 2021-07-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("gene_sequencing")
@ApiModel(value="GeneSequencing对象", description="")
public class GeneSequencingVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("Name")
    private String name;

    @TableField("Bacteria")
    private String bacteria;

    @TableField("Serotype")
    private String serotype;

    @TableField("ST")
    private String st;

    @TableField("host")
    private String host;

    @TableField("Year")
    private String year;

    @TableField("Country")
    private String country;

    @TableField("Province")
    private String province;

    @TableField("Continent")
    private String continent;

    @TableField("whether_MIC")
    private String whetherMic;

    @TableField("ENR")
    private String enr;

    @TableField("OFL")
    private String ofl;

    @TableField("AMP")
    private String amp;

    @TableField("GEN")
    private String gen;

    @TableField("KAN")
    private String kan;

    @TableField("TET")
    private String tet;

    @TableField("AZI")
    private String azi;

    @TableField("NAL")
    private String nal;

    @TableField("CIP")
    private String cip;

    @TableField("CHL")
    private String chl;

    @TableField("SXT")
    private String sxt;

    @TableField("STR")
    private String str;

    @TableField("AMC")
    private String amc;

    @TableField("SIZ")
    private String siz;

    @TableField("CF")
    private String cf;

    @TableField("CEF")
    private String cef;

    @TableField("CX")
    private String cx;

    @TableField("COL")
    private String col;

    @TableField("MPN")
    private String mpn;

    @TableField("AorC")
    private String aorc;

    @TableField("SPT")
    private String spt;

    @TableField("FFC")
    private String ffc;

    @TableField("SF")
    private String sf;

    @TableField("CAZ")
    private String caz;

    @TableField("MEM")
    private String mem;

    @TableField("APR")
    private String apr;

    @TableField("CL")
    private String cl;

    @TableField("MEQ")
    private String meq;

    @TableField("TRI")
    private String tri;

    @TableField("SUL")
    private String sul;

    @TableField("AMO")
    private String amo;

    @TableField("CLA")
    private String cla;

    @TableField("IPM")
    private String ipm;

    @TableField("AMK")
    private String amk;

    @TableField("CFZ")
    private String cfz;

    @TableField("ATM")
    private String atm;

    @TableField("OTC")
    private String otc;

    @TableField("PMB")
    private String pmb;

    @TableField("FIS")
    private String fis;

    @TableField("XNL")
    private String xnl;

    @TableField("whether_joining_together")
    private String whetherJoiningTogether;

    @TableField("sequencing_file_download_link")
    private String sequencingFileDownloadLink;

    @TableField("CgMLST")
    private String cgmlst;

    @TableField("Resfinder")
    private String resfinder;

    @TableField("VFDB")
    private String vfdb;


}
