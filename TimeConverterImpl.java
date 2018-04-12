package com.paic.arch.interviews;

public class TimeConverterImpl implements TimeConverter {

	@Override
	public String convertTime(String aTime) {
		
		// 如果传入的时间为空时，抛異常
		if (aTime == null || "".equals(aTime)) {
			throw new NullPointerException(aTime);
		}

		// 装小时
		int Hours = 0;
		// 装分钟
		int Minutes = 0;
		// 装秒
		int Seconds = 0;

		// 用来存 set theory clock 的显示
		StringBuffer theoryClock = new StringBuffer();

		// 截取传入aTime的 时，分，秒
		String arrTime[] = aTime.split(":");

		// 如果截取到时分秒，则把截取到的时分秒赋值给时、分、秒变量
		if (arrTime.length == 3) {
			Hours = Integer.parseInt(arrTime[0]);
			Minutes = Integer.parseInt(arrTime[1]);
			Seconds = Integer.parseInt(arrTime[2]);
		}

		// 顶端每两秒钟闪烁一次的黄灯，亮黄色为偶数，用Y表示；暗色为奇数，用O表示
		switch (Seconds % 2) {
		case 0:
			theoryClock.append("Y\r\n");
			break;
		default:
			theoryClock.append("O\r\n");
		}

		/**
		 * 从顶端数第二排每盏灯代表5小时，红色为5个小时，用R表示；暗色为不够5个小时，用O表示 0时或者24小时全暗
		 */
		switch (Hours / 5) {
		// 不到5个小时，或者24小时，没有灯亮
		case 0:
			theoryClock.append("OOOO\r\n");
			break;
		// 5个小时不到10个小时，第一盏灯亮
		case 1:
			theoryClock.append("ROOO\r\n");
			break;
		// 10个小时不到15个小时，第一、第二盏灯亮
		case 2:
			theoryClock.append("RROO\r\n");
			break;
		// 15个小时不到20个小时，第一、二、三盏灯亮
		case 3:
			theoryClock.append("RRRO\r\n");
			break;
		// 20个小时不到24个小时，四盏灯全量
		case 4:
			theoryClock.append("RRRR\r\n");
			break;
		default:
			break;
		}

		/**
		 * 从顶端数第三排每盏灯代表1小时，红色为1个小时，用R表示；暗色为不够1个小时，用O表示.
		 * 每5个小时第三排灯全暗，第二排灯加亮一盏灯，24小时第二第三排灯全暗
		 */
		switch (Hours % 5) {
		// 每5小时，如5、15、20，第三排灯全暗
		case 0:
			theoryClock.append("OOOO\r\n");
			break;
		// 每5小时后满1小时，第一盏灯亮
		case 1:
			theoryClock.append("ROOO\r\n");
			break;
		// 每5小时后满2小时，第一、二盏灯亮
		case 2:
			theoryClock.append("RROO\r\n");
			break;
		// 每5小时后满3小时，第一、二、三盏灯亮
		case 3:
			theoryClock.append("RRRO\r\n");
			break;
		// 每5小时后满4小时，第三排灯全亮
		case 4:
			theoryClock.append("RRRR\r\n");
			break;
		default:
			break;
		}

		/**
		 * 第四排有11盏灯，每盏灯代表5分钟，其中第3、第6和第9盏的灯是红色，表示15分、30分和45分，其他的灯为黄色。
		 * 红色亮用R表示，黄色亮用Y表示，暗色用O表示。 
		 * 1小时 = 60 分钟， Minutes 最大值为59，60时为一个小时，第二、三排根据情况变化
		 */
		switch (Minutes / 5) {
		// 不到5分钟，没有灯亮
		case 0:
			theoryClock.append("OOOOOOOOOOO\r\n");
			break;
		// 5分钟，第一盏灯亮黄色
		case 1:
			theoryClock.append("YOOOOOOOOOO\r\n");
			break;
		// 10分钟。第一、二盏灯亮黄色
		case 2:
			theoryClock.append("YYOOOOOOOOO\r\n");
			break;
		// 15分钟，第一、二盏灯亮黄色，第三盏灯亮红色
		case 3:
			theoryClock.append("YYROOOOOOOO\r\n");
			break;
		// 20分钟，第一、二盏灯亮黄色，第三盏灯亮红色，第四盏灯亮黄色
		case 4:
			theoryClock.append("YYRYOOOOOOO\r\n");
			break;
		// 25分钟
		case 5:
			theoryClock.append("YYRYYOOOOOO\r\n");
			break;
		// 30分钟
		case 6:
			theoryClock.append("YYRYYROOOOO\r\n");
			break;
		// 35分钟
		case 7:
			theoryClock.append("YYRYYRYOOOO\r\n");
			break;
		// 40分钟
		case 8:
			theoryClock.append("YYRYYRYYOOO\r\n");
			break;
		// 45分钟
		case 9:
			theoryClock.append("YYRYYRYYROO\r\n");
			break;
		// 50分钟
		case 10:
			theoryClock.append("YYRYYRYYRYO\r\n");
			break;
		// 55分钟
		case 11:
			theoryClock.append("YYRYYRYYRYY\r\n");
			break;
		default:
			break;
		}

		/**
		 * 最后一排即第五排有4盏灯，每盏灯代表1分钟,黄色亮为1分钟，用Y表示；暗色表示不到1分钟，用O表示。 
		 * 每5分钟，第五排全暗，上三排根据情况变化
		 */
		switch (Minutes % 5) {
		// 每5分钟，第五排等全暗
		case 0:
			theoryClock.append("OOOO");
			break;
		// 每5分钟后满1分钟，第一盏灯亮
		case 1:
			theoryClock.append("YOOO");
			break;
		// 每5分钟后满2分钟
		case 2:
			theoryClock.append("YYOO");
			break;
		// 每5分钟后满3分钟
		case 3:
			theoryClock.append("YYYO");
			break;
		// 每5分钟后满4分钟
		case 4:
			theoryClock.append("YYYY");
			break;
		default:
			break;
		}
		return theoryClock.toString();
	}
}
