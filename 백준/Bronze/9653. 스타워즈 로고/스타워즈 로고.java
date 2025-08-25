import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		sb.append("    8888888888  888    88888\n");
		sb.append("   88     88   88 88   88  88\n");
		sb.append("    8888  88  88   88  88888\n");
		sb.append("       88 88 888888888 88   88\n");
		sb.append("88888888  88 88     88 88    888888\n");
		sb.append("\n");
		sb.append("88  88  88   888    88888    888888\n");
		sb.append("88  88  88  88 88   88  88  88\n");
		sb.append("88 8888 88 88   88  88888    8888\n");
		sb.append(" 888  888 888888888 88  88      88\n");
		sb.append("  88  88  88     88 88   88888888");

		System.out.println(sb.toString());
	}
}