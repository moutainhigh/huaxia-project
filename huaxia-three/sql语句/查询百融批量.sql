select count(*) from (
select  * from bairong_als_trn_batch_detail b where b.batch_file_id in(
select batch_file_id from bairong_als_trn_batch_file where batch_id in(
select batch_id from bairong_als_trn_batch where crt_time between to_date('2019-11-21 00:00:00','yyyy-mm-dd hh24:mi:ss') and 
to_date('2019-12-10 23:59:59','yyyy-mm-dd hh24:mi:ss')
)
)
) temp where temp.trn_id  not in(
select trn_id from bairong_als_base 
)