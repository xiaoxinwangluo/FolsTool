package top.fols.box.io.base;

import java.io.IOException;
import java.io.OutputStream;
import top.fols.box.io.interfaces.XInterfaceFixedLengthStream;
import top.fols.box.io.interfaces.XInterfaceGetOriginStream;

/*
 * @java.io.OutputStream
 * Constraint OutputStream Max Write Size
 * 限制输出流最大写入大小
 */
public class XOutputStreamFixedLength<T extends OutputStream> extends OutputStream implements XInterfaceFixedLengthStream,XInterfaceGetOriginStream<T> {
	private T stream;
	protected long maxCount;
	protected long nowCount;
	protected boolean fixed;
	public XOutputStreamFixedLength(T stream, long maxCount) {
		if (null == stream)
			throw new NullPointerException("stream for null");
		if (maxCount < 0)
			maxCount = 0;
		this.stream = stream;
		this.maxCount = maxCount;
		this.nowCount = 0;
		this.fixed = true;
	}

	// 写入一个字节b到“字节数组输出流”中，并将计数+1  
	@Override
	public void write(int b) throws IOException {  
		if (fixed && nowCount + 1 > maxCount)
			return;
		stream.write(b);
		nowCount++;
	}  
	// 写入字节数组b到“字节数组输出流”中。off是“写入字节数组b的起始位置”，len是写入的长度  
	@Override
	public  void write(byte b[], int off, int len) throws IOException {
		if (len < 0)
			return;
		else if (fixed) {
			if (nowCount + len > maxCount) {
				len = (int)(maxCount - nowCount);
				if (len < 1)
					return;
			}
			if (nowCount + len > maxCount)
				return;
		}
		stream.write(b, off, len);
		nowCount += len;
	}  
	@Override
	public void close() throws IOException {
		stream.close();
	}
	@Override
	public void flush() throws IOException {
		stream.flush();
	}



	@Override
	public long getFreeLength() {
		return maxCount - nowCount;
	}
	@Override
	public boolean isAvailable() {
		return !fixed || fixed && nowCount < maxCount;
	}

	@Override
	public long getUseLength() {
		return nowCount;
	}
	@Override
	public void resetUseLength() {
		nowCount = 0;
	}

	@Override
	public long getMaxUseLength() {
		return maxCount;
	}
	@Override
	public void setMaxUseLength(long maxCount) {
		this.maxCount = maxCount;
	}

	@Override
	public void fixed(boolean b) {
		this.fixed = b;
	}
	@Override
	public boolean isFixed() {
		return fixed;
	}


	@Override
	public T getStream() {
		return stream;
	}

}

