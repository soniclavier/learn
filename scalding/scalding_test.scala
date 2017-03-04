import com.twitter.scalding._
 class scripttest(args:Args) extends Job(args){
 	val insurance = Csv("/Users/vviswanath/Documents/scalding_example/FL_insurance_sample.csv", 
 		fields = List('policyID,'statecode,'county,'eq_site_limit,'hu_site_limit,'fl_site_limit,'fr_site_limit,'tiv_2011,'tiv_2012,'eq_site_deductible,'hu_site_deductible,'fl_site_deductible,'fr_site_deductible,'point_latitude,'point_longitude,'line,'construction,'point_granularity))
 	insurance
 		.read
 		.groupBy('county){_.average('eq_site_limit).average('hu_site_limit).sum[Double]('fl_site_limit)}
 		.write(Csv("/Users/vviswanath/Documents/scalding_example/ins_out.csv"))
 }
