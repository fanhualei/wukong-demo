package com.wukong.examples.dao;

import com.wukong.examples.model.Student;
import com.wukong.examples.model.StudentExample.Criteria;
import com.wukong.examples.model.StudentExample.Criterion;
import com.wukong.examples.model.StudentExample;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;

public class StudentSqlProvider {

    public String countByExample(StudentExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("wk_student");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(StudentExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("wk_student");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(Student record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("wk_student");
        
        if (record.getStudentName() != null) {
            sql.VALUES("student_name", "#{studentName,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentAge() != null) {
            sql.VALUES("student_age", "#{studentAge,jdbcType=INTEGER}");
        }
        
        if (record.getStudentSex() != null) {
            sql.VALUES("student_sex", "#{studentSex,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(StudentExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("student_id");
        } else {
            sql.SELECT("student_id");
        }
        sql.SELECT("student_name");
        sql.SELECT("student_age");
        sql.SELECT("student_sex");
        sql.FROM("wk_student");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        Student record = (Student) parameter.get("record");
        StudentExample example = (StudentExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("wk_student");
        
        if (record.getStudentId() != null) {
            sql.SET("student_id = #{record.studentId,jdbcType=BIGINT}");
        }
        
        if (record.getStudentName() != null) {
            sql.SET("student_name = #{record.studentName,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentAge() != null) {
            sql.SET("student_age = #{record.studentAge,jdbcType=INTEGER}");
        }
        
        if (record.getStudentSex() != null) {
            sql.SET("student_sex = #{record.studentSex,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("wk_student");
        
        sql.SET("student_id = #{record.studentId,jdbcType=BIGINT}");
        sql.SET("student_name = #{record.studentName,jdbcType=VARCHAR}");
        sql.SET("student_age = #{record.studentAge,jdbcType=INTEGER}");
        sql.SET("student_sex = #{record.studentSex,jdbcType=INTEGER}");
        
        StudentExample example = (StudentExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Student record) {
        SQL sql = new SQL();
        sql.UPDATE("wk_student");
        
        if (record.getStudentName() != null) {
            sql.SET("student_name = #{studentName,jdbcType=VARCHAR}");
        }
        
        if (record.getStudentAge() != null) {
            sql.SET("student_age = #{studentAge,jdbcType=INTEGER}");
        }
        
        if (record.getStudentSex() != null) {
            sql.SET("student_sex = #{studentSex,jdbcType=INTEGER}");
        }
        
        sql.WHERE("student_id = #{studentId,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, StudentExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}