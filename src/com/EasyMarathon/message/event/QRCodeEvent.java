package com.EasyMarathon.message.event;

public class QRCodeEvent {
	// �¼�KEYֵ
		private String EventKey;
		// ���ڻ�ȡ��ά��ͼƬ
		private String Ticket;

		public String getEventKey() {
			return EventKey;
		}

		public void setEventKey(String eventKey) {
			EventKey = eventKey;
		}

		public String getTicket() {
			return Ticket;
		}

		public void setTicket(String ticket) {
			Ticket = ticket;
		}
}
