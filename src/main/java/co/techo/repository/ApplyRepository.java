package co.techo.repository;

import co.techo.entity.applies.ApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ApplyRepository extends JpaRepository<ApplyEntity, Long> {
    //    String SQL_SELECT_ALL = "select * from applies a where a.member_id = 66";
//    String SQL_SELECT_ALL = "select * from courses a where a.id = 66";
    String SQL_SELECT_ALL = "select * from members a where a.id = 66";

    String SQL_SELECT_COURSE_SIGNING_CERTIFICATE_2 = "SELECT\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    m.age AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                ELSE m.status = 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (select \n" +
            "    \tcase \n" +
            "    \t\twhen a.van = 'yes' then 'Y'\n" +
            "    \t\telse 'N'\n" +
            "    \tend\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone AS 'เบอร์ติดต่อ',\n" +
            "    null AS 'เรือนพัก',\n" +
            "    null AS 'ที่นั่ง',\n" +
            "    null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "ORDER BY\n" +
            "    CASE\n" +
            "        WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "        WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "        WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "        WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    END";

    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_ALL, nativeQuery = true)
    List<Object> selectAll();

//    @Modifying
//    @Transactional
//    @Query(value = SQL_SELECT_COURSE_SIGNING_CERTIFICATE, nativeQuery = true)
//    List<Object> findCourseSigningCertificate(@Param("course_id") int course_id);

    String SQL_SELECT_COURSE_SIGNING_CERTIFICATE = "SELECT\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    m.age AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                ELSE m.status = 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (select \n" +
            "    \tcase \n" +
            "    \t\twhen a.van = 'yes' then 'Y'\n" +
            "    \t\telse 'N'\n" +
            "    \tend\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone AS 'เบอร์ติดต่อ'\n" +
            "--     null AS 'เรือนพัก',\n" +
            "--     null AS 'ที่นั่ง',\n" +
            "--     null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "ORDER BY\n" +
            "    CASE\n" +
            "        WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "        WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "        WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "        WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    END";

    String SQL_SELECT_COURSE_SIGNING_CERTIFICATE_ANAPANASATI_1_DAY = "SELECT\n" +
            "    m.status AS 'สถานะศิษย์',\n" +
            "    a.created_at AS 'สมัครเมื่อ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    YEAR(c.date_start)-YEAR(m.birthdate) AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                WHEN m.status != 'ผู้สมัครใหม่' THEN 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (\n" +
            "        select\n" +
            "            case\n" +
            "                when a.van = 'yes' then 'Y'\n" +
            "                else 'N'\n" +
            "            end\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone_slug AS 'เบอร์ติดต่อ' --     null AS 'เรือนพัก',\n" +
            "    --     null AS 'ที่นั่ง',\n" +
            "    --     null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "    and c.category = 'คอร์สอานาปานสติ ๑ วัน'\n" +
            "    and a.confirmed = 'Yes' \n" +
            "    and m.gender = :gender\n" +
            "    and m.status in ('ผู้สมัครใหม่', 'ศิษย์อานาฯ ๑ วัน')\n" +
            "ORDER BY\n" +
            "    CASE\n" +
            "        WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "        WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "        WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "        WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    END,\n" +
            "    m.age DESC,\n" +
            "     convert (m.name using tis620) ASC,\n" +
            "--     Case\n" +
            "--         when m.status = 'ผู้สมัครใหม่' THEN 0\n" +
            "--         when m.status = 'ศิษย์อานาฯ ๑ วัน' THEN 0\n" +
            "--         when m.status = 'ศิษย์อานาปานสติ' THEN 1\n" +
            "--         when m.status = 'ศิษย์เตโชวิปัสสนา' THEN 2\n" +
            "--    \tEND,\n" +
            "    a.created_at ASC\n" +
            "LIMIT :limit";
    String SQL_SELECT_COURSE_SIGNING_CERTIFICATE_ANAPANASATI_3_DAYS = "SELECT\n" +
            "    m.status AS 'สถานะศิษย์',\n" +
            "    a.created_at AS 'สมัครเมื่อ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    YEAR(c.date_start)-YEAR(m.birthdate) AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                 WHEN m.status != 'ผู้สมัครใหม่' THEN 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (\n" +
            "        select\n" +
            "            case\n" +
            "                when a.van = 'yes' then 'Y'\n" +
            "                else 'N'\n" +
            "            end\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone_slug AS 'เบอร์ติดต่อ' --     null AS 'เรือนพัก',\n" +
            "    --     null AS 'ที่นั่ง',\n" +
            "    --     null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "    and c.category = 'คอร์สอานาปานสติ'\n" +
            "    and a.confirmed = 'Yes' \n" +
            "    and m.gender = :gender\n" +
            "    and m.status in ('ผู้สมัครใหม่','ศิษย์อานาฯ ๑ วัน','ศิษย์อานาปานสติ','ศิษย์เตโชวิปัสสนา')\n" +
            "ORDER BY\n" +
            "    CASE\n" +
            "        WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "        WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "        WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "        WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    END,\n" +
            "    m.age DESC,\n" +
            "     convert (m.name using tis620) ASC,\n" +
            "--     Case\n" +
            "--         when m.status = 'ผู้สมัครใหม่' THEN 0\n" +
            "--         when m.status = 'ศิษย์อานาฯ ๑ วัน' THEN 0\n" +
            "--         when m.status = 'ศิษย์อานาปานสติ' THEN 1\n" +
            "--         when m.status = 'ศิษย์เตโชวิปัสสนา' THEN 2\n" +
            "--    \tEND,\n" +
            "    a.created_at ASC\n" +
            "LIMIT :limit";

    String SQL_SELECT_COURSE_SIGNING_CERTIFICATE_TECHO = "SELECT\n" +
            "    m.status AS 'สถานะศิษย์',\n" +
            "    a.created_at AS 'สมัครเมื่อ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    YEAR(c.date_start)-YEAR(m.birthdate) AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                 WHEN m.status != 'ผู้สมัครใหม่' THEN 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (\n" +
            "        select\n" +
            "            case\n" +
            "                when a.van = 'yes' then 'Y'\n" +
            "                else 'N'\n" +
            "            end\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone_slug AS 'เบอร์ติดต่อ' --     null AS 'เรือนพัก',\n" +
            "    --     null AS 'ที่นั่ง',\n" +
            "    --     null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "    and c.category = 'คอร์สเตโชวิปัสสนา'\n" +
            "    and a.confirmed = 'Yes' \n" +
            "    and m.gender = :gender\n" +
            "    and m.status in ('ผู้สมัครใหม่', 'ศิษย์อานาฯ ๑ วัน','ศิษย์อานาปานสติ','ศิษย์เตโชวิปัสสนา')\n" +
            "ORDER BY\n" +
            "    CASE\n" +
            "        WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "        WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "        WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "        WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    END,\n" +
            "    m.age DESC,\n" +
            "     convert (m.name using tis620) ASC,\n" +
            "--     Case\n" +
            "--         when m.status = 'ผู้สมัครใหม่' THEN 0\n" +
            "--         when m.status = 'ศิษย์อานาฯ ๑ วัน' THEN 0\n" +
            "--         when m.status = 'ศิษย์อานาปานสติ' THEN 1\n" +
            "--         when m.status = 'ศิษย์เตโชวิปัสสนา' THEN 2\n" +
            "--    \tEND,\n" +
            "    a.created_at ASC\n" +
            "LIMIT :limit";
    String SQL_SELECT_COURSE_SIGNING_CERTIFICATE_ALUMNI_3_DAYS = "SELECT\n" +
            "    m.status AS 'สถานะศิษย์',\n" +
            "    a.created_at AS 'สมัครเมื่อ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    YEAR(c.date_start) - YEAR(m.birthdate) AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                WHEN m.status != 'ผู้สมัครใหม่' THEN 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (\n" +
            "        select\n" +
            "            case\n" +
            "                when a.van = 'yes' then 'Y'\n" +
            "                else 'N'\n" +
            "            end\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone_slug AS 'เบอร์ติดต่อ' --     null AS 'เรือนพัก',\n" +
            "    --     null AS 'ที่นั่ง',\n" +
            "    --     null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "    and a.confirmed = 'Yes' \n" +
            "    and m.gender = :gender\n" +
            "    and c.category = 'คอร์สศิษย์เก่า (๓ วัน)'\n" +
            "ORDER BY\n" +
            "    CASE\n" +
            "        WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "        WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "        WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "        WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    END,\n" +
            "    m.age DESC,\n" +
            "    convert (m.name using tis620) ASC,\n" +
            "    a.created_at ASC\n" +
            "LIMIT :limit";

    String SQL_SELECT_COURSE_CONFIRM_ALUMNI_3_DAYS = "SELECT\n" +
            "    m.status AS 'สถานะศิษย์',\n" +
            "    a.created_at AS 'สมัครเมื่อ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    YEAR(c.date_start) - YEAR(m.birthdate) AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                WHEN m.status != 'ผู้สมัครใหม่' THEN 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (\n" +
            "        select\n" +
            "            case\n" +
            "                when a.van = 'yes' then 'Y'\n" +
            "                else 'N'\n" +
            "            end\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone AS 'เบอร์ติดต่อ' --     null AS 'เรือนพัก',\n" +
            "    --     null AS 'ที่นั่ง',\n" +
            "    --     null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "    and c.category = 'คอร์สศิษย์เก่า (๓ วัน)'\n" +
            "    and a.confirmed = 'no'\n" +
            "    and m.gender = :gender --     and m.status in (:status);\n" +
            "    and m.status in (\n" +
            "        'ผู้สมัครใหม่',\n" +
            "        'ศิษย์อานาฯ ๑ วัน',\n" +
            "        'ศิษย์อานาปานสติ',\n" +
            "        'ศิษย์เตโชวิปัสสนา'\n" +
            "    )\n" +
            "    and DATEDIFF(\n" +
            "        c.date_start,\n" +
            "        DATE_FORMAT(a.created_at, \"%Y-%m-%d\")\n" +
            "    ) > :date_diff \n" +
            "ORDER BY\n" +
            "    --     CASE\n" +
            "    --         WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "    --         WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "    --         WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "    --         WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    --     END,\n" +
            "    --         m.age DESC,\n" +
            "    --     convert (m.name using tis620) ASC, \n" +
            "    --     Case\n" +
            "    --         when m.status = 'ผู้สมัครใหม่' THEN 0\n" +
            "    --         when m.status = 'ศิษย์อานาฯ ๑ วัน' THEN 0\n" +
            "    --         when m.status = 'ศิษย์อานาปานสติ' THEN 1\n" +
            "    --         when m.status = 'ศิษย์เตโชวิปัสสนา' THEN 2\n" +
            "    --    \tEND,\n" +
            "    a.created_at ASC\n" +
            "LIMIT\n" +
            "    :limit";

    String SQL_SELECT_COURSE_CONFIRM_ANAPANASATI_3_DAYS = "SELECT\n" +
            "    m.status AS 'สถานะศิษย์',\n" +
            "    a.created_at AS 'สมัครเมื่อ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    YEAR(c.date_start) - YEAR(m.birthdate) AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                WHEN m.status != 'ผู้สมัครใหม่' THEN 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (\n" +
            "        select\n" +
            "            case\n" +
            "                when a.van = 'yes' then 'Y'\n" +
            "                else 'N'\n" +
            "            end\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone AS 'เบอร์ติดต่อ' --     null AS 'เรือนพัก',\n" +
            "    --     null AS 'ที่นั่ง',\n" +
            "    --     null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "    and c.category = 'คอร์สอานาปานสติ'\n" +
            "    and a.confirmed = 'no'\n" +
            "    and m.gender = :gender --     and m.status in (:status);\n" +
            "    and m.status in (\n" +
            "        'ผู้สมัครใหม่',\n" +
            "        'ศิษย์อานาฯ ๑ วัน',\n" +
            "        'ศิษย์อานาปานสติ',\n" +
            "        'ศิษย์เตโชวิปัสสนา'\n" +
            "    )\n" +
            "    and DATEDIFF(\n" +
            "        c.date_start,\n" +
            "        DATE_FORMAT(a.created_at, \"%Y-%m-%d\")\n" +
            "    ) > :date_diff \n" +
            "ORDER BY\n" +
            "    --     CASE\n" +
            "    --         WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "    --         WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "    --         WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "    --         WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    --     END,\n" +
            "    --         m.age DESC,\n" +
            "    --     convert (m.name using tis620) ASC, \n" +
            "\t\tCase\n" +
            "            when m.status = 'ผู้สมัครใหม่' THEN 0\n" +
            "            when m.status = 'ศิษย์อานาฯ ๑ วัน' THEN 1\n" +
            "            when m.status = 'ศิษย์อานาปานสติ' THEN 2\n" +
            "            when m.status = 'ศิษย์เตโชวิปัสสนา' THEN 3\n" +
            "       \tEND,\n" +
            "       \ta.created_at ASC\n" +
            "LIMIT\n" +
            "    :limit";

    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_COURSE_CONFIRM_ANAPANASATI_3_DAYS, nativeQuery = true)
    String[][] findCourseConfirmAnapanasati3Days(
            @Param("course_id") int course_id, @Param("limit") int limit,
            @Param("gender") String gender,
            @Param("date_diff") int date_diff);

    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_COURSE_CONFIRM_ALUMNI_3_DAYS, nativeQuery = true)
    String[][] findCourseConfirmAlumni3Days(
            @Param("course_id") int course_id, @Param("limit") int limit,
            @Param("gender") String gender,
            @Param("date_diff") int date_diff);

    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_COURSE_SIGNING_CERTIFICATE, nativeQuery = true)
    List<Object> findCourseSigningCertificate(@Param("course_id") int course_id);

    //course_id test => 500
    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_COURSE_SIGNING_CERTIFICATE_ANAPANASATI_3_DAYS, nativeQuery = true)
    String[][] findCourseSigningCertificateAnapanasati3Days(@Param("course_id") int course_id,
                                                            @Param("limit") int limit,
                                                            @Param("gender") String gender);

    //course_id test => 503
    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_COURSE_SIGNING_CERTIFICATE_ALUMNI_3_DAYS, nativeQuery = true)
    String[][] findCourseSigningCertificateAlumni3Days(@Param("course_id") int course_id,
                                                       @Param("limit") int limit,
                                                       @Param("gender") String gender);

    //course_id test => 501
    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_COURSE_SIGNING_CERTIFICATE_ANAPANASATI_1_DAY, nativeQuery = true)
    String[][] findCourseSigningCertificateAnapanasati1Day(@Param("course_id") int course_id,
                                                           @Param("limit") int limit,
                                                           @Param("gender") String gender);

    //course_id test => 504
    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_COURSE_SIGNING_CERTIFICATE_TECHO, nativeQuery = true)
    String[][] findCourseSigningCertificateTecho(@Param("course_id") int course_id,
                                                 @Param("limit") int limit,
                                                 @Param("gender") String gender);

    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_VAN_SIGNING, nativeQuery = true)
    String[][] findVanSigningData(@Param("course_id") int course_id,
                                  @Param("limit") int limit,
                                  @Param("gender") String gender);

    String SQL_SELECT_VAN_SIGNING = "SELECT\n" +
            "    m.status AS 'สถานะศิษย์',\n" +
            "    a.created_at AS 'สมัครเมื่อ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    YEAR(c.date_start) - YEAR(m.birthdate) AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                WHEN m.status != 'ผู้สมัครใหม่' THEN 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (\n" +
            "        select\n" +
            "            case\n" +
            "                when a.van = 'yes' then 'Y'\n" +
            "                else 'N'\n" +
            "            end\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone_slug AS 'เบอร์ติดต่อ' --     null AS 'เรือนพัก',\n" +
            "    --     null AS 'ที่นั่ง',\n" +
            "    --     null AS 'ลงชื่อ'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "    and a.confirmed = 'Yes'\n" +
            "    and m.gender = :gender --     and m.status in (:status);\n" +
            "    and m.status in (\n" +
            "        'ผู้สมัครใหม่',\n" +
            "        'ศิษย์อานาฯ ๑ วัน',\n" +
            "        'ศิษย์อานาปานสติ',\n" +
            "        'ศิษย์เตโชวิปัสสนา'\n" +
            "    )\n" +
            "ORDER BY\n" +
            "    CASE\n" +
            "        WHEN m.buddhism = 'ภิกษุ' THEN 0\n" +
            "        WHEN m.buddhism = 'สามเณร' THEN 1\n" +
            "        WHEN m.buddhism = 'แม่ชี' THEN 2\n" +
            "        WHEN m.buddhism = 'ฆราวาส' THEN 3\n" +
            "    END,\n" +
            "    --     m.age DESC,\n" +
            "    convert (m.name using tis620) ASC --     Case\n" +
            "    --         when m.status = 'ผู้สมัครใหม่' THEN 0\n" +
            "    --         when m.status = 'ศิษย์อานาฯ ๑ วัน' THEN 0\n" +
            "    --         when m.status = 'ศิษย์อานาปานสติ' THEN 1\n" +
            "    --         when m.status = 'ศิษย์เตโชวิปัสสนา' THEN 2\n" +
            "    --    \tEND,\n" +
            "    --     a.created_at ASC\n" +
            "LIMIT\n" +
            "    :limit";


    String SQL_SELECT_COURSE_CONFIRM_ANAPANASATI_1_DAY = "SELECT\n" +
            "    m.status AS 'สถานะศิษย์',\n" +
            "    a.created_at AS 'สมัครเมื่อ',\n" +
            "--     m.id as 'member_id', --> K'Art\n" +
            "--     a.id as 'applie_id', --> K'ARt\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.buddhism = 'ภิกษุ' THEN 'พระ'\n" +
            "                WHEN m.buddhism = 'สามเณร' THEN 'สามเณร'\n" +
            "                WHEN m.buddhism = 'ฆราวาส' THEN 'คุณ'\n" +
            "                WHEN m.buddhism = 'แม่ชี' THEN 'แม่ชี'\n" +
            "            END\n" +
            "    ) AS 'คำนำ',\n" +
            "    m.name AS 'ชื่อ',\n" +
            "    m.surname AS 'นามสกุล',\n" +
            "    m.nickname AS 'ชื่อเล่น',\n" +
            "    YEAR(c.date_start) - YEAR(m.birthdate) AS 'อายุ',\n" +
            "    (\n" +
            "        SELECT\n" +
            "            CASE\n" +
            "                WHEN m.status = 'ผู้สมัครใหม่' THEN 'N'\n" +
            "                WHEN m.status != 'ผู้สมัครใหม่' THEN 'O'\n" +
            "            END\n" +
            "    ) AS 'ศิษย์',\n" +
            "    (\n" +
            "        select\n" +
            "            case\n" +
            "                when a.van = 'yes' then 'Y'\n" +
            "                else 'N'\n" +
            "            end\n" +
            "    ) as 'รถตู้',\n" +
            "    m.phone AS 'เบอร์ติดต่อ',\n" +
            "     (\n" +
            "        SELECT\n" +
            "            c2.date_start \n" +
            "        FROM\n" +
            "            applies a2\n" +
            "            inner join courses c2 on a2.course_id = c2.id\n" +
            "        where\n" +
            "            a2.member_id in (m.id)\n" +
            "            and a2.state = 'ผ่านการอบรม'\n" +
            "        order by\n" +
            "            c2.date_start desc\n" +
            "        limit\n" +
            "            1\n" +
            "    ) as 'last_course'\n" +
            "FROM\n" +
            "    applies a\n" +
            "    INNER JOIN members m ON m.id = a.member_id\n" +
            "    INNER JOIN courses c ON c.id = a.course_id\n" +
            "WHERE\n" +
            "    c.id = :course_id\n" +
            "    and c.category = 'คอร์สอานาปานสติ ๑ วัน'\n" +
            "    and a.confirmed = 'no'\n" +
            "    and m.gender = :gender \n" +
            "    and m.status in (\n" +
            "        'ผู้สมัครใหม่',\n" +
            "        'ศิษย์อานาฯ ๑ วัน' \n" +
            "    )\n" +
            "    and DATEDIFF(\n" +
            "        c.date_start,\n" +
            "        DATE_FORMAT(a.created_at, \"%Y-%m-%d\")\n" +
            "    ) > :date_diff\n" +
            "ORDER BY\n" +
            "    Case\n" +
            "        when m.status = 'ผู้สมัครใหม่' THEN 0\n" +
            "        when m.status = 'ศิษย์อานาฯ ๑ วัน' THEN 1\n" +
            "    END,\n" +
            "    last_course ASC,\n" +
            "    a.created_at ASC,\n" +
            "    convert (m.name using tis620) ASC\n" +
            "LIMIT\n" +
            "    :limit";

    @Modifying
    @Transactional
    @Query(value = SQL_SELECT_COURSE_CONFIRM_ANAPANASATI_1_DAY, nativeQuery = true)
    String[][] findCourseConfirmAnapanasati1Day(@Param("course_id") int course_id,
                                                @Param("limit") int limit,
                                                @Param("gender") String gender,
                                                @Param("date_diff") int date_diff);
}

