package com.common.dto;

import java.io.Serializable;

public class DrugDto implements Serializable{
    /**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */
	private static final long serialVersionUID = -1001416950713975021L;

	private Long id;

    private String drugcode;

    private String drugname;

    private String drugnamepinyin;

    private String spec;

    private String agentname;

    private String manufacturername;

    private String dosepkgname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugcode() {
        return drugcode;
    }

    public void setDrugcode(String drugcode) {
        this.drugcode = drugcode == null ? null : drugcode.trim();
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname == null ? null : drugname.trim();
    }

    public String getDrugnamepinyin() {
        return drugnamepinyin;
    }

    public void setDrugnamepinyin(String drugnamepinyin) {
        this.drugnamepinyin = drugnamepinyin == null ? null : drugnamepinyin.trim();
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec == null ? null : spec.trim();
    }

    public String getAgentname() {
        return agentname;
    }

    public void setAgentname(String agentname) {
        this.agentname = agentname == null ? null : agentname.trim();
    }

    public String getManufacturername() {
        return manufacturername;
    }

    public void setManufacturername(String manufacturername) {
        this.manufacturername = manufacturername == null ? null : manufacturername.trim();
    }

    public String getDosepkgname() {
        return dosepkgname;
    }

    public void setDosepkgname(String dosepkgname) {
        this.dosepkgname = dosepkgname == null ? null : dosepkgname.trim();
    }
}