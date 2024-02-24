package com.springboot.spring.com;

import lombok.Data;

@Data
public class Pagination {
    
	/*
	 * �ּ��� ���� �ܾ� ���� ������: ȭ�� �Ʒ��� ��ġ�ϴ� Ư�� �������� ��Ī�ϴ� ���� �ε���: Ư�� ���������� Ư�� �Խù��� �����ϴ� ����
	 */

	int rowCount = 20; // �� ������ �� ������ �Խù� ����
	int pageCount = 3; // �� ���� �� ���� ������ ����
	int totalCount; // �� �Խù� ����
	int page; // ���� ������

	int startPage = 1; // �� ���� ���� ������: �⺻ �� 1 // ex) 1 2 3 4 5 �� �� 1�� �ǹ�.
	int endPage; // �� ���� �� ������

	int totalPageCount; // �� ������ ����

	boolean isPrev = false; // ���� �������� �̵��ϴ� ��ư ����
	boolean isNext = false; // ���� �������� �̵��ϴ� ��ư ����

	int offset; // ��ŭ ��� ������ ���ΰ�.
	
	  /* �˻� Ű���� */
    private String item_Name;

	/* ���̵� */
	private String id;
    
    private String LOGIN_MEMBER_ID; //�α��� ���� ����

	public Pagination(final int totalCount, final int page) {

		// �� ������ ���� ���ϱ�
		setTotalPageCount(totalCount, this.rowCount);

		// �� ���� ù ������ ���ϱ�
		setStartPage(this.startPage, page, this.pageCount);

		// �� ���� �� ������ ���ϱ�
		setEndpage(this.startPage, this.pageCount, this.totalPageCount);

		// ���� ��� ��ư ���� �Ǻ��ϱ�
		isPrev(page, this.pageCount);

		// ���� ��� ��ư ���� �Ǻ��ϱ�
		isNext(this.endPage, this.totalPageCount);

		// offset ���ϱ�
		setOffset(page, this.rowCount);
	}

	// �� ������ ���� ���ϱ�
	private void setTotalPageCount(final int totalCount, final int rowCount) {
		this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
	}

	// �� ���� ù ������ ���ϱ�
	private void setStartPage(final int startPage, final int page, final int pageCount) {
		this.startPage = startPage + (((page - startPage) / pageCount) * pageCount);
	}

	// �� ���� �� ������ ���ϱ�
	private void setEndpage(final int startPage, final int pageCount, final int totalPageCount) {
		this.endPage = ((startPage - 1) + pageCount) < totalPageCount ? (startPage - 1) + pageCount : totalPageCount;
	}

	// ���� ������ �̵��� ��ư ���� ����
	private void isPrev(final int page, final int pageCount) {
		this.isPrev = 1 < ((page * 1.0) / pageCount);
	}

	// ���� ������ �̵��� ��ư ���� ����
	private void isNext(final int endPage, final int totalPageCount) {
		this.isNext = endPage < totalPageCount;
	}

	// offset ���ϱ� // ���� select �� ��� ��������
	private void setOffset(final int page, final int rowCount) {
		this.offset = (page - 1) * rowCount;
	}

	@Override
	public String toString() {
		return "Pagination [rowCount=" + rowCount + ", pageCount=" + pageCount + ", totalCount=" + totalCount
				+ ", page=" + page + ", startPage=" + startPage + ", endPage=" + endPage + ", totalPageCount="
				+ totalPageCount + ", isPrev=" + isPrev + ", isNext=" + isNext + ", offset=" + offset + ", item_Name="
				+ item_Name + "]";
	}

}
